package com.example.feature_moviedetail.presentation

import com.example.core.domain.MovieEntity

data class MovieDetailsViewState(
    val movie: MovieEntity,
    val isFavorite: Boolean
)
