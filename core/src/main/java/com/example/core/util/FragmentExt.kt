package com.example.core.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.example.core.presentation.Event
import com.example.core.presentation.EventsQueue
import java.util.Queue
import javax.inject.Provider

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

@MainThread
inline fun <reified T : ViewModel> Fragment.viewModelFromProvider(
    crossinline providerProducer: () -> Provider<T>
): Lazy<T> {
    val factory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return providerProducer.invoke().get() as T
        }
    }
    return viewModels { factory }
}

inline fun <reified T : ViewBinding> Fragment.viewBindings(
    crossinline bind: (View) -> T
) = object : Lazy<T> {
    private var cached: T? = null

    private val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_DESTROY) {
            cached = null
        }
    }

    override val value: T
        get() = cached ?: bind(requireView()).also {
            viewLifecycleOwner.lifecycle.addObserver(observer)
            cached = it
        }

    override fun isInitialized(): Boolean = cached != null
}

fun Fragment.hideKeyboard() {
    val inputManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
        ?: return
    val windowToken = view?.rootView?.windowToken
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}
