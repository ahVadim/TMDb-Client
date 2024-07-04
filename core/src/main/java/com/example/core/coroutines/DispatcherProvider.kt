package com.example.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

open class DispatcherProvider @Inject constructor() {

    open fun ui(): CoroutineDispatcher = Dispatchers.Main.immediate
    open fun io(): CoroutineDispatcher = Dispatchers.IO
    open fun computation(): CoroutineDispatcher = Dispatchers.Default
}
