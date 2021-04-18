package com.yusupovdev.myfinder.di.modules

import android.content.Context
import com.yusupovdev.myfinder.data.MainRepository
import com.yusupovdev.myfinder.data.TmdbApi
import com.yusupovdev.myfinder.data.preference.PreferenceProvider
import com.yusupovdev.myfinder.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
// Передаем контекст для SharedPreference через конструктор
class DomainModule(val context: Context) {
    // НАм нухно контекст провадить, поэтому создаем такой метод
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    // Создаем кземпляр SharedPreferences
    fun providePreferences(context: Context) = PreferenceProvider(context)

    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi, preferenceProvider: PreferenceProvider) = Interactor(repo = repository, retrofitService = tmdbApi, preferences = preferenceProvider)
}
