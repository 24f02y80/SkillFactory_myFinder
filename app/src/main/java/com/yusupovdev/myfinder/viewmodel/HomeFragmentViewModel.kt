package com.yusupovdev.myfinder.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusupovdev.myfinder.App
import com.yusupovdev.myfinder.data.Entity.Film
import com.yusupovdev.myfinder.domain.Interactor
import java.util.concurrent.Executors
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData()

    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor
    val filmsListLiveData: LiveData<List<Film>>

    init {
        App.instance.dagger.inject(this)
        filmsListLiveData = interactor.getFilmsFromDB()
        getFilms()
         }

    fun getFilms() {
        showProgressBar.postValue(true)
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess() {
                showProgressBar.postValue(false)

            }
            // Вызывается когда пролблемы с сетью
            override fun onFailure() {
                showProgressBar.postValue(false)                }

        })

    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
    }
}