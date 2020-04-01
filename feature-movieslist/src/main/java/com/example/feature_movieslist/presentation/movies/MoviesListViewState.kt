package com.example.feature_movieslist.presentation.movies

import com.example.feature_movieslist.domain.MovieEntity

data class MoviesListViewState(
    val moviesList: List<MovieEntity>
)
