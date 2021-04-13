package com.yusupovdev.myfinder.di

import com.yusupovdev.myfinder.di.modules.DatabaseModule
import com.yusupovdev.myfinder.di.modules.DomainModule
import com.yusupovdev.myfinder.di.modules.RemoteModule
import com.yusupovdev.myfinder.viewmodel.HomeFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    //метод для того, чтобы появилась внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
}