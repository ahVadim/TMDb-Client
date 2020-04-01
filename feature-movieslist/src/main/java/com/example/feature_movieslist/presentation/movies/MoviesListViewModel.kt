package com.example.feature_movieslist.presentation.movies

import androidx.lifecycle.MutableLiveData
import com.example.core.presentation.BaseViewModel
import javax.inject.Inject

class MoviesListViewModel @Inject constructor() : BaseViewModel() {

    var liveState = MutableLiveData<MoviesListViewState>(createInitialState())

    private fun createInitialState(): MoviesListViewState {
        return MoviesListViewState(
            emptyList()
        )
    }
}
