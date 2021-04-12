package com.yusupovdev.myfinder.domain

import com.yusupovdev.myfinder.data.MainRepository

class Interactor (val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}