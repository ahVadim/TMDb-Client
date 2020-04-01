package com.example.feature_movieslist.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesSearchResponseDto(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<MovieDto>,
    @Json(name = "total_results") val total_results: Int,
    @Json(name = "total_pages") val total_pages: Int
)
