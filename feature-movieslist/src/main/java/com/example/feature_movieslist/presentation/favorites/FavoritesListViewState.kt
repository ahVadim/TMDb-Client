package com.example.feature_movieslist.presentation.favorites

import com.example.core.domain.MovieEntity
import com.example.core.presentation.statedelegate.ListViewState

data class FavoritesListViewState(
    val listState: ListViewState<MovieEntity>,
    val isGridLayout: Boolean
)
