package com.yusupovdev.myfinder.di.modules

import com.yusupovdev.myfinder.data.MainRepository
import com.yusupovdev.myfinder.data.TmdbApi
import com.yusupovdev.myfinder.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) = Interactor(repo = repository, retrofitService = tmdbApi)
}
