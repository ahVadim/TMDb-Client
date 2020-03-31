package com.example.feaure_authorization.presentation.view

sealed class AuthErrorState {
    object TryLater : AuthErrorState()
    object IncorrectData : AuthErrorState()
    object None : AuthErrorState()
}
