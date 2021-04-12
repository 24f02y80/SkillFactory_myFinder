package com.yusupovdev.myfinder.data

interface TmdbApi {
    @GET("3/movie/popular")
    fun getFilms(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Int
    ): Call<TmdbResults>
}