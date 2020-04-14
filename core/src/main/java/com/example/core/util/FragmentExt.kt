package com.example.core.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import com.example.core.presentation.Event
import com.example.core.presentation.EventsQueue
import java.util.Queue

inline fun <T, L : LiveData<T>> Fragment.observe(liveData: L, crossinline block: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner) { block(it) }
}

fun Fragment.observe(eventsQueue: EventsQueue, eventHandler: (Event) -> Unit) {
    eventsQueue.observe(viewLifecycleOwner) { queue: Queue<Event>? ->
        while (queue != null && queue.isNotEmpty()) {
            eventHandler(queue.remove())
        }
    }
}
