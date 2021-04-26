package com.yusupovdev.myfinder.di.modules

import android.content.Context
import androidx.room.Room
import com.yusupovdev.myfinder.data.DAO.FilmDao
import com.yusupovdev.myfinder.data.MainRepository
import com.yusupovdev.myfinder.data.db.AppDatabase
import com.yusupovdev.myfinder.data.db.DatabaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseHelper(context: Context) =
        Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "film_db"
    ).build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: FilmDao) = MainRepository(filmDao)
}