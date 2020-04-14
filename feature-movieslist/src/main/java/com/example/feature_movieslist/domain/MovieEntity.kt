package com.example.feature_movieslist.domain

data class MovieEntity(
    val id: Int,
    val posterUrl: String?,
    val title: String,
    val subtitle: String,
    val genre: String,
    val rating: Double,
    val ratingCount: Int,
    val duration: String?
)
