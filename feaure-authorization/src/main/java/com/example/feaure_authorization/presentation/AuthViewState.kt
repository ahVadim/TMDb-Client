package com.example.feaure_authorization.presentation

data class AuthViewState(
    val login: String?,
    val password: String?,
    val errorState: AuthErrorState,
    val isLoginButtonEnabled: Boolean
)
