package com.example.core.presentation.statedelegate

import android.view.View
import androidx.core.view.isVisible

class StateDelegate<T>(
    private val contentView: View,
    private val loadingView: View,
    private val errorView: View,
    private val showData: (T) -> Unit
) {

    fun renderState(state: ViewState<T>) {
        when (state) {
            is ViewState.Data -> {
                contentView.isVisible = true
                loadingView.isVisible = false
                errorView.isVisible = false
                showData.invoke(state.data)
            }
            is ViewState.Loading -> {
                contentView.isVisible = false
                loadingView.isVisible = true
                errorView.isVisible = false
            }
            is ViewState.Error -> {
                contentView.isVisible = false
                loadingView.isVisible = false
                errorView.isVisible = true
            }
        }
    }
}
