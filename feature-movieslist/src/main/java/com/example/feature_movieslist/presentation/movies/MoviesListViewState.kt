package com.example.feature_movieslist.presentation.movies

import com.example.core.domain.MovieEntity

data class MoviesListViewState(
    val moviesList: List<MovieEntity>
)
