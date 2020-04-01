package com.example.feature_movieslist.presentation.list

data class MovieItemViewModel(
    val id: Int,
    val posterUrl: String?,
    val title: String,
    val subtitle: String,
    val genre: String,
    val rating: String,
    val ratingCount: Int,
    val duration: String
)
