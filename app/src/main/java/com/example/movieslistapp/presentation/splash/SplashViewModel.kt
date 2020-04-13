package com.example.movieslistapp.presentation.splash

import com.example.core.prefs.UserPrefs
import com.example.core.presentation.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    userPrefs: UserPrefs
) : BaseViewModel() {

    init {
        if (userPrefs.sessionId.isNullOrBlank()) {
            navigateTo(SplashFragmentDirections.actionSplashToAuth())
        } else {
            navigateTo(SplashFragmentDirections.actionSplashToPincode())
        }
    }
}
