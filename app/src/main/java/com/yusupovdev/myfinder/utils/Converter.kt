package com.yusupovdev.myfinder.utils

import com.yusupovdev.myfinder.data.Entity.TmdbFilm
import com.yusupovdev.myfinder.data.Entity.Film

object Converter {
    fun convertApiListToDtoList(list: List<TmdbFilm>?): List<Film> {
        val result = mutableListOf<Film>()
        list?.forEach {
            result.add(Film(
                title = it.title,
                poster = it.posterPath,
                description = it.overview,
                rating = it.voteAverage,
                isInFavorites = false
            ))
        }
        return result
    }
}