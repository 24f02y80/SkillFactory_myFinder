package com.yusupovdev.myfinder.data.Entity

data class TmdbResultsDto(
        @Serialize dName("page")
        val page: Int,
        @SerializedName("results")
        val tmdbFilms: List<TmdbFilm>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)