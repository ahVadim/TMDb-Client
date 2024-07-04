package com.example.core.util

import kotlinx.coroutines.CancellationException
import timber.log.Timber

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
