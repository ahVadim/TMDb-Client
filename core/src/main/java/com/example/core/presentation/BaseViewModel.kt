package com.example.core.presentation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.core.presentation.events.NavEvent
import com.example.core.presentation.events.ParentNavEvent
import com.example.core.util.delegate
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow

abstract class BaseViewModel<State>(initialState: State) : ViewModel(), StateOwner<State> {

    final override val stateFlow = MutableStateFlow(initialState)
    protected var state by stateFlow.delegate()

    private val eventChannel = Channel<Event>(capacity = Channel.UNLIMITED)
    final override val eventFlow: Flow<Event> = eventChannel.consumeAsFlow()

    protected fun sendEvent(event: Event) = eventChannel.trySend(event)

    protected fun navigateTo(destination: NavDirections) {
        eventChannel.trySend(NavEvent(destination))
    }

    protected fun parentNavigateTo(destination: NavDirections) {
        eventChannel.trySend(ParentNavEvent(destination))
    }
}
