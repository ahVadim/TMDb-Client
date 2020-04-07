package com.example.feature_movieslist.presentation.movies

import com.example.core.domain.MovieEntity
import com.example.core.presentation.statedelegate.ListViewState

data class MoviesListViewState(
    val listState: ListViewState<MovieEntity>,
    val isGridLayout: Boolean
)
