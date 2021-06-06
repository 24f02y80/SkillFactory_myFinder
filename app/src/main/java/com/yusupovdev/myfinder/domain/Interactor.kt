package com.yusupovdev.myfinder.domain

import com.yusupovdev.myfinder.data.Entity.Film
import com.yusupovdev.myfinder.data.Entity.TmdbResultsDto
import com.yusupovdev.myfinder.data.MainRepository
import com.yusupovdev.myfinder.data.TmdbApi
import com.yusupovdev.myfinder.data.preference.PreferenceProvider
import com.yusupovdev.myfinder.utils.Converter
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()

    //В конструктор мы будем передавать коллбэк из вью модели, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, которую нужно загрузить (это для пагинации)

    fun getFilmsFromApi(page: Int) {
        // показываем ProgressBar
        progressBarState.onNext(true)

        //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список фильмов
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object :
            Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
                // Кладем фильмы в БД
                // При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список фильмов
                Completable.fromSingle <List<Film>>{
                    repo.putToDb(list)
                }
                    .subscribeOn(Schedulers.io())
                    .subscribe()
                progressBarState.onNext(false)
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                progressBarState.onNext(false)
            }
        })
    }
    // Метод сохранения насроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }


    // Метод получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    // Метод получения метода репозитория для вытаскивания фильма из БД
    fun getFilmsFromDB(): Observable<List<Film>> = repo.getAllFromDb()
}