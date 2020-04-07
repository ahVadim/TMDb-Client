package com.example.feature_moviedetail.presentation

import androidx.lifecycle.MutableLiveData
import com.example.core.domain.MovieEntity
import com.example.core.presentation.BaseViewModel
import com.example.core.util.delegate
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor() : BaseViewModel() {

    val liveState = MutableLiveData<MovieDetailsViewState>()
    private var state by liveState.delegate()

    fun setMovie(movie: MovieEntity) {
        state = MovieDetailsViewState(movie)
    }
}
