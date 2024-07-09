package com.example.core.presentation

import kotlinx.coroutines.flow.Flow

interface StateOwner<S> {
    val stateFlow: Flow<S>
    val eventFlow: Flow<Event>
}