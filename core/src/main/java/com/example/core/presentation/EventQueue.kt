package com.example.core.presentation

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import java.util.LinkedList
import java.util.Queue

class EventsQueue : MutableLiveData<Queue<Event>>() {

    @MainThread
    fun offer(event: Event) {
        val queue = (value ?: LinkedList()).apply {
            add(event)
        }
        value = queue
    }
}
