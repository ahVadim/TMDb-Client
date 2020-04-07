package com.example.core.presentation.statedelegate

sealed class ListViewState<T> {
    class Data<T>(val data: List<T>) : ListViewState<T>()
    class Loading<T> : ListViewState<T>()
    class Error<T> : ListViewState<T>()
}
