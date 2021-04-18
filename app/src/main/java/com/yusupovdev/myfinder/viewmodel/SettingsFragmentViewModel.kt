package com.yusupovdev.myfinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusupovdev.myfinder.App
import com.yusupovdev.myfinder.domain.Interactor
import javax.inject.Inject

class SettingsFragmentViewModel: ViewModel() {
    //Инжектим интерактор
    @Inject
    lateinit var interactor: Interactor
    val categoryPropertyLifeData: MutableLiveData<String> = MutableLiveData()

    init {
        App.instance.dagger.inject(this)
            // получаем категорию при иницилизации, чтобы у нас сразу подтягивалась категория
            getCategoryProperty()
    }

    fun getCategoryProperty() {
        // Кладем категорию в LiveData
        categoryPropertyLifeData.value = interactor.getDefaultCategoryFromPreferences()
    }

    fun putCategoryProperty(category: String) {
        // Сахраняем настройки
        interactor.saveDefaultCategoryToPreferences(category)
        // И сразу забираем, чтобы сохранить состояние в модели
        getCategoryProperty()
    }

}
