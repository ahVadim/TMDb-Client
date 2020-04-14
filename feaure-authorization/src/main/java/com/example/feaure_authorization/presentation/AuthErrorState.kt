package com.example.feaure_authorization.presentation

sealed class AuthErrorState {
    object TryLater : AuthErrorState()
    object IncorrectData : AuthErrorState()
    object None : AuthErrorState()
}
