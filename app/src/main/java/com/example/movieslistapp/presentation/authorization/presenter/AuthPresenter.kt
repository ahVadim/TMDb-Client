package com.example.movieslistapp.presentation.authorization.presenter

import com.example.movieslistapp.domain.authorization.AuthInteractor
import com.example.movieslistapp.presentation.authorization.view.AuthView
import moxy.MvpPresenter
import javax.inject.Inject

class AuthPresenter @Inject constructor(
    private val authInteractor: AuthInteractor
): MvpPresenter<AuthView>() {

    init {
        viewState.setLoginButtonEnable(isEnable = false)
    }

    fun onUserDataChange(login: String?, password: String?) {
        if (login.isNullOrBlank() || password.isNullOrBlank()) {
            viewState.setLoginButtonEnable(isEnable = false)
        } else {
            viewState.setLoginButtonEnable(isEnable = true)
        }
    }
}
