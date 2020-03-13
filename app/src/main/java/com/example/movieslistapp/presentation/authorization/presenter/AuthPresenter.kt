package com.example.movieslistapp.presentation.authorization.presenter

import com.example.movieslistapp.domain.authorization.AuthInteractor
import com.example.movieslistapp.exceptions.AuthException
import com.example.movieslistapp.presentation.BasePresenter
import com.example.movieslistapp.presentation.authorization.view.AuthView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthPresenter @Inject constructor(
    private val authInteractor: AuthInteractor
) : BasePresenter<AuthView>() {

    private var currentLogin: String = ""
    private var currentPassword: String = ""

    init {
        viewState.setLoginButtonEnable(isEnable = false)
    }

    fun onUserDataChange(login: String?, password: String?) {
        if (login.isNullOrBlank() || password.isNullOrBlank()) {
            viewState.setLoginButtonEnable(isEnable = false)
        } else {
            currentLogin = login
            currentPassword = password
            viewState.setLoginButtonEnable(isEnable = true)
        }
        viewState.hideError()
    }

    fun onLoginButtonClick() {
        authInteractor.authorize(currentLogin, currentPassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                           viewState.showSuccessAuthorizationMessage()
                           viewState.hideError()

                       }, { e ->
                           when (e) {
                               is AuthException -> {
                                   viewState.setLoginButtonEnable(isEnable = false)
                                   viewState.showIncorrectDataError()
                               }
                               else -> {
                                   viewState.showTryLaterError()
                               }
                           }
                       })
            .let(compositeDisposable::add)
    }
}
