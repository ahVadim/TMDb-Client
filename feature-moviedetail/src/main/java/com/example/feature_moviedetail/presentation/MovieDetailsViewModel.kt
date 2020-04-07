package com.example.feature_moviedetail.presentation

import androidx.lifecycle.MutableLiveData
import com.example.core.presentation.BaseViewModel
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor() : BaseViewModel() {

    var liveState = MutableLiveData<MovieDetailsViewState>()
}
