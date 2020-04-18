package com.example.feature_moviedetail.presentation

import androidx.lifecycle.MutableLiveData
import com.example.core.domain.MovieEntity
import com.example.core.presentation.BaseViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class MovieDetailsViewModel @AssistedInject constructor(
    @Assisted private val movie: MovieEntity
) : BaseViewModel() {

    val liveState = MutableLiveData(MovieDetailsViewState(movie))

    @AssistedInject.Factory
    interface Factory {

        fun create(movie: MovieEntity): MovieDetailsViewModel
    }
}
