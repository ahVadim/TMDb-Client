package com.example.core.util

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <T, R> T.runCatchingCancellable(
    action: T.() -> R,
    onSuccess: (R) -> Unit = {},
    onError: (Throwable) -> Unit = {}
) {
    try {
        val result = action.invoke(this)
        onSuccess.invoke(result)
    } catch (error: Throwable) {
        if (error is CancellationException) {
            throw error
        } else {
            Timber.e(error)
            onError.invoke(error)
        }
    }
}

fun <State> MutableStateFlow<State>.delegate(): ReadWriteProperty<Any, State> {
    return object : ReadWriteProperty<Any, State> {
        override fun setValue(thisRef: Any, property: KProperty<*>, value: State) {
            this@delegate.value = value
        }

        override fun getValue(thisRef: Any, property: KProperty<*>): State {
            return this@delegate.value
        }
    }
}
