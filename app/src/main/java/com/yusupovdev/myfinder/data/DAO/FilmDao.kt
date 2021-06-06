package com.yusupovdev.myfinder.data.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yusupovdev.myfinder.data.Entity.Film
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

import kotlinx.coroutines.flow.Flow

// Помечаем, что это не просто интерфейс, а Dao - обьект
@Dao
interface FilmDao {
    //Запрос на всю таблицу
    @Query("SELECT * FROM cached_films")
    fun getCachedFilms(): Observable<List<Film>>

    //Кладем списком в БД, в случае коныликта перезаписываем
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Film>)
}