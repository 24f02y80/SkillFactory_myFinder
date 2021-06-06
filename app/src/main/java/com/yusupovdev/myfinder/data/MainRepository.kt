package com.yusupovdev.myfinder.data
import com.yusupovdev.myfinder.data.DAO.FilmDao
import com.yusupovdev.myfinder.data.Entity.Film
import io.reactivex.rxjava3.core.Observable

import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы в бд должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }


    fun getAllFromDb(): Observable<List<Film>> = filmDao.getCachedFilms()

}