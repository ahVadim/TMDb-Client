package com.example.movieslistapp.presentation.authorization.presenter

import com.example.core.exceptions.AuthException
import com.example.core.presentation.BasePresenter
import com.example.movieslistapp.domain.authorization.AuthInteractor
import com.example.movieslistapp.presentation.authorization.view.AuthView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthPresenter @Inject constructor(
    private val authInteractor: AuthInteractor
) : BasePresenter<AuthView>() {

    init {
        viewState.setLoginButtonEnable(isEnable = false)
    }

    fun onUserDataChange(login: String?, password: String?) {
        viewState.setLoginButtonEnable(!login.isNullOrBlank() && !password.isNullOrBlank())
        viewState.hideError()
    }

    fun onLoginButtonClick(login: String, password: String) {
        authInteractor.authorize(login, password)
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
