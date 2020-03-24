package com.example.movieslistapp.presentation

import com.example.core.prefs.UserPrefs
import moxy.MvpPresenter
import javax.inject.Inject

class AppPresenter @Inject constructor(
    private val userPrefs: UserPrefs
) : MvpPresenter<AppView>() {

    override fun onFirstViewAttach() {
        if (userPrefs.sessionId != null) {
            viewState.openMainScreen()
        } else {
            viewState.openAuthScreen()
        }
    }
}
