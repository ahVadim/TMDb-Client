package com.example.core.presentation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.core.presentation.events.NavEvent
import com.example.core.presentation.events.ParentNavEvent

open class BaseViewModel : ViewModel() {

    val eventsQueue = EventsQueue()

    protected fun navigateTo(destination: NavDirections) {
        eventsQueue.offer(NavEvent(destination))
    }

    protected fun parentNavigateTo(destination: NavDirections) {
        eventsQueue.offer(ParentNavEvent(destination))
    }
}
