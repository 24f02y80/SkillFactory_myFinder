package com.yusupovdev.myfinder.data
import android.content.ContentValues
import android.database.Cursor
import com.yusupovdev.myfinder.R
import com.yusupovdev.myfinder.domain.Film

import com.yusupovdev.myfinder.data.db.DatabaseHelper

class MainRepository (databaseHelper: DatabaseHelper) {
    /*val filmsDataBase = listOf(
            Film("American Ultra", R.drawable.americanultra, "This should be a description", 9f),
            Film("Divergent", R.drawable.divergent, "This should be a description", 7.8f),
            Film("Avatar", R.drawable.ktoia, "This should be a description", 6.3f),
            Film("Кто Я", R.drawable.scale_1200, "This should be a description", 3.3f),
            Film("Пираты Корибского моря", R.drawable.marsianin, "This should be a description", 8.3f),
            Film("Шпионский мост", R.drawable.tomhanks, "This should be a description", 7.2f),
            Film("Titanik", R.drawable.titanik, "This should be a description", 6.7f),
            Film("Высотка", R.drawable.visotka, "This should be a description", 5.3f)
    )*/

    //Инициализаруем обьект для взаимодействия с БД
    private val sqlDb = databaseHelper.readableDatabase
    //Создаем курсор для обработки запросов из БД
    private lateinit var cursor:Cursor

    fun putToDb(film: Film) {
        //Создаем обьект, который будет хранить пары ключ-значение, для того
        // стобы класть нужные данные в нужные столбцы
        val cv = ContentValues()
        cv.apply {
            put(DatabaseHelper.COLUMN_TITLE, film.title)
            put(DatabaseHelper.COLUMN_POSTER, film.poster)
            put(DatabaseHelper.COLUMN_DESCREPTION, film.description)
            put(DatabaseHelper.COLUMN_RATING, film.rating)
        }
        //Кладем фильм в БД
        sqlDb.insert(DatabaseHelper.TABLE_NAME, null, cv)
    }

    fun getAllFromDb(): List<Film> {
        //Создаем курсор на основании запроса "Получить все из таблицы"
        cursor = sqlDb.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME}", null)
        //Сюда будем сахранять результат получения данных
        val result = mutableListOf<Film>()
        //проверяем, есть ли хоть одна строка в ответе на запрос
        if (cursor.moveToFirst()) {
            //Итерируемся по таблице, пока есть записи и создаем на основании обьект Film
            do {
                val title = cursor.getString(1)
                val poster = cursor.getString(2)
                val description = cursor.getString(3)
                val rating = cursor.getDouble(4)

                result.add(Film(title, poster, description, rating))
                } while (cursor.moveToNext())
        }
        // Возвращаем список фильмов
        return result
    }
}