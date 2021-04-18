package com.yusupovdev.myfinder

import android.app.Application
import com.yusupovdev.myfinder.di.AppComponent
import com.yusupovdev.myfinder.di.DaggerAppComponent
import com.yusupovdev.myfinder.di.modules.DatabaseModule
import com.yusupovdev.myfinder.di.modules.DomainModule
import com.yusupovdev.myfinder.di.modules.RemoteModule

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.builder()
                .remoteModule(RemoteModule())
                .databaseModule(DatabaseModule())
                .domainModule(DomainModule(this))
                .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
