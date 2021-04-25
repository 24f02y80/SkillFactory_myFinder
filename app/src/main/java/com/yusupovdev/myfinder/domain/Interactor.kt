package com.yusupovdev.myfinder.domain

import com.yusupovdev.myfinder.API
import com.yusupovdev.myfinder.data.Entity.TmdbResultsDto
import com.yusupovdev.myfinder.data.MainRepository
import com.yusupovdev.myfinder.data.TmdbApi
import com.yusupovdev.myfinder.data.preference.PreferenceProvider
import com.yusupovdev.myfinder.utils.Converter
import com.yusupovdev.myfinder.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    //В конструктор мы будем передавать коллбэк из вью модели, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, которую нужно загрузить (это для пагинации)

    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object :
            retrofit2.Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                //При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список фильмов
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
                // Кладем фильмы в БД
                list.forEach { repo.putToDb(film = it) }
                callback.onSuccess(list)
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
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
    fun getFilmsFromDB(): List<Film> = repo.getAllFromDb()
}