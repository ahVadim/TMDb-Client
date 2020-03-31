package com.example.movieslistapp.presentation.splash

import com.example.core.navigation.NavEvent
import com.example.core.prefs.UserPrefs
import com.example.core.presentation.BaseViewModel
import com.example.core.presentation.EventsQueue
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    userPrefs: UserPrefs
) : BaseViewModel() {

    val eventsQueue = EventsQueue()

    init {
        if (userPrefs.sessionId.isNullOrBlank()) {
            eventsQueue.offer(NavEvent.AuthEvent)
        } else {
            eventsQueue.offer(NavEvent.MainScreen)
        }
    }
}
