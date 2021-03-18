package com.example.core.presentation

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

interface AssistedViewModelFactory<T : ViewModel> {

    fun create(handle: SavedStateHandle): T
}

fun <T : ViewModel> AssistedViewModelFactory<T>.toSavedStateViewModelFactory(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) = object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return create(handle) as T
    }
}
