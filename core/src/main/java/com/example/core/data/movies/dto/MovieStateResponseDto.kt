package com.example.core.data.movies.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieStateResponseDto(
    @Json(name = "id") val id: Int,
    @Json(name = "favorite") val isFavorite: Boolean,
    @Json(name = "watchlist") val isInWatchlist: Boolean
)
