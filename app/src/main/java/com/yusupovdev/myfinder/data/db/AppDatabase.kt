package com.yusupovdev.myfinder.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yusupovdev.myfinder.data.DAO.FilmDao
import com.yusupovdev.myfinder.data.Entity.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}