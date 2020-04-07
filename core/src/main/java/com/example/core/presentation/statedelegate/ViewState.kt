package com.example.core.presentation.statedelegate

sealed class ViewState<T> {
    class Data<T>(val data: T) : ViewState<T>()
    class Loading<T> : ViewState<T>()
    class Error<T> : ViewState<T>()
}
