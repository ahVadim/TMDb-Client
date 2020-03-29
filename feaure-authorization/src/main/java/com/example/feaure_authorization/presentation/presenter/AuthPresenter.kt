package com.example.feaure_authorization.presentation.presenter

import com.example.core.exceptions.AuthException
import com.example.core.presentation.BasePresenter
import com.example.core.rxjava.SchedulersProvider
import com.example.core.util.ioToMain
import com.example.feaure_authorization.domain.AuthInteractor
import com.example.feaure_authorization.presentation.view.AuthErrorState
import com.example.feaure_authorization.presentation.view.AuthView
import timber.log.Timber
import javax.inject.Inject

class AuthPresenter @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val schedulers: SchedulersProvider
) : BasePresenter<AuthView>() {

    init {
        viewState.setLoginButtonEnable(isEnable = false)
    }

    fun onUserDataChange(login: String?, password: String?) {
        viewState.setLoginButtonEnable(!login.isNullOrBlank() && !password.isNullOrBlank())
        viewState.setErrorState(AuthErrorState.None)
    }

    fun onLoginButtonClick(login: String, password: String) {
        authInteractor.authorize(login, password)
            .ioToMain(schedulers)
            .subscribe({
                           viewState.showSuccessAuthorizationMessage()
                           viewState.setErrorState(AuthErrorState.None)
                       }, { error ->
                           Timber.e(error)
                           when (error) {
                               is AuthException -> {
                                   viewState.setLoginButtonEnable(isEnable = false)
                                   viewState.setErrorState(AuthErrorState.IncorrectData)
                               }
                               else -> {
                                   viewState.setErrorState(AuthErrorState.TryLater)
                               }
                           }
                       })
            .let(compositeDisposable::add)
    }
}
