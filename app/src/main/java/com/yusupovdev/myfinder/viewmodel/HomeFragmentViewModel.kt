package com.yusupovdev.myfinder.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusupovdev.myfinder.App
import com.yusupovdev.myfinder.data.Entity.Film
import com.yusupovdev.myfinder.domain.Interactor
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {

    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor
    val filmsListData: Observable<List<Film>>
    val showProgressBar: BehaviorSubject<Boolean>

    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBarState
        filmsListData = interactor.getFilmsFromDB()
        getFilms()
         }

    fun getFilms() {
        interactor.getFilmsFromApi(1)

    }
}