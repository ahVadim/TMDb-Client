package com.example.movieslistapp.presentation.authorization.presenter

import com.example.movieslistapp.domain.authorization.AuthInteractor
import com.example.movieslistapp.presentation.authorization.view.AuthView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class AuthPresenter @Inject constructor(
    private val authInteractor: AuthInteractor
) : MvpPresenter<AuthView>() {

    private val compositeDisposable = CompositeDisposable()

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
            .subscribe({ isSuccess ->
                           if (isSuccess) {
                               viewState.showSuccessAuthorizationMessage()
                               viewState.hideError()
                           } else {
                               viewState.setLoginButtonEnable(isEnable = false)
                               viewState.showIncorrectDataError()
                           }
                       }, {
                           viewState.showTryLaterError()
                       })
            .let(compositeDisposable::add)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}
