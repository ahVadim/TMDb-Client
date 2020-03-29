package com.example.feature_profile.presentation

import androidx.lifecycle.MutableLiveData
import com.example.core.presentation.BaseViewModel
import com.example.core.util.delegate

class ProfileViewModel : BaseViewModel() {
    var liveState = MutableLiveData<ProfileViewState>(createInitialState())
    private var state by liveState.delegate()

    private fun createInitialState(): ProfileViewState {
        return ProfileViewState(
            userName = "Mick Wick",
            userMail = "examle@mail.com"
        )
    }

    fun onLogoutButtonClick() {
    }
}
