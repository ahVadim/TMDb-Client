package com.example.feature_movieslist.data.dto

import com.squareup.moshi.Json

data class MovieDto(
    @Json(name = "poster_path") val poster_path: String,
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "overview") val overview: String,
    @Json(name = "release_date") val release_date: String,
    @Json(name = "genre_ids") val genre_ids: List<Int>,
    @Json(name = "id") val id: Int,
    @Json(name = "original_title") val original_title: String,
    @Json(name = "original_language") val original_language: String,
    @Json(name = "title") val title: String,
    @Json(name = "backdrop_path") val backdrop_path: String,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "vote_count") val vote_count: Int,
    @Json(name = "video") val video: Boolean,
    @Json(name = "vote_average") val vote_average: Double
)
