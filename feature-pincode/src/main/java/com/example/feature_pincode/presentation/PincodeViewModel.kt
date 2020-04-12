package com.example.feature_pincode.presentation

import androidx.lifecycle.MutableLiveData
import com.example.core.presentation.BaseViewModel
import javax.inject.Inject

class PincodeViewModel @Inject constructor() : BaseViewModel() {

    var liveState = MutableLiveData<PincodeViewState>(createInitialState())

    private fun createInitialState(): PincodeViewState {
        return PincodeViewState(
            isPincodeErrorVisible = false,
            userMail = "examle@mail.com"
        )
    }
}
