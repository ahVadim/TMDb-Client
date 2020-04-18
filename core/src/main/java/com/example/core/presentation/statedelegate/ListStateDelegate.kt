package com.example.core.presentation.statedelegate

import android.view.View
import androidx.core.view.isVisible

class ListStateDelegate<T>(
    private val contentView: View,
    private val emptyStateView: View? = null,
    private val loadingView: View? = null,
    private val errorView: View? = null,
    private val showData: (List<T>) -> Unit,
    private val shouldHideContentWhenLoading: Boolean = true
) {

    fun renderState(state: ListViewState<T>) {
        when (state) {
            is ListViewState.Data -> {
                if (state.data.isEmpty() && emptyStateView != null) {
                    contentView.isVisible = false
                    emptyStateView.isVisible = true
                    loadingView?.isVisible = false
                    errorView?.isVisible = false
                } else {
                    showData.invoke(state.data)
                    contentView.isVisible = true
                    emptyStateView?.isVisible = false
                    loadingView?.isVisible = false
                    errorView?.isVisible = false
                }
            }
            is ListViewState.Loading -> {
                if (loadingView == null) throw IllegalArgumentException()
                if (shouldHideContentWhenLoading) {
                    contentView.isVisible = false
                }
                emptyStateView?.isVisible = false
                loadingView.isVisible = true
                errorView?.isVisible = false
            }
            is ListViewState.Error -> {
                if (errorView == null) throw IllegalArgumentException()
                contentView.isVisible = false
                emptyStateView?.isVisible = false
                loadingView?.isVisible = false
                errorView.isVisible = true
            }
        }
    }
}
