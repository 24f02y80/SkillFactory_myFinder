package com.yusupovdev.myfinder

import android.app.Application
import com.yusupovdev.myfinder.di.AppComponent
import com.yusupovdev.myfinder.di.DaggerAppComponent

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
