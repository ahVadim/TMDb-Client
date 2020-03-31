package com.example.core.navigation

import com.example.core.presentation.Event

sealed class NavEvent : Event {
    object AuthEvent : NavEvent()
    object MainScreen : NavEvent()
}
