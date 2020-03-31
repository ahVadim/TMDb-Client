package com.example.feaure_authorization.presentation.presenter

import com.example.feaure_authorization.presentation.view.AuthErrorState

data class AuthViewState(
    val login: String?,
    val password: String?,
    val errorState: AuthErrorState,
    val isLoginButtonEnabled: Boolean
)
