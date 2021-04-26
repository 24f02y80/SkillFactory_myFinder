package com.yusupovdev.myfinder.data
import android.content.ContentValues
import android.database.Cursor
import com.yusupovdev.myfinder.data.DAO.FilmDao
import com.yusupovdev.myfinder.data.Entity.Film

import com.yusupovdev.myfinder.data.db.DatabaseHelper
import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы в бд должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }


    fun getAllFromDb(): List<Film> {
        return filmDao.getCachedFilms()
    }
}