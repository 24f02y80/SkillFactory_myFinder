package com.yusupovdev.myfinder.data.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yusupovdev.myfinder.data.Entity.Film

// Помечаем, что это не просто интерфейс, а Dao - обьект
@Dao
interface FilmDao {
    //Запрос на всю таблицу
    @Query("SELECT * FROM cached_films")
    fun getCachedFilms(): List<Film>

    //Кладем списком в БД, в случае коныликта перезаписываем
    @Insert(OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Film>)
}