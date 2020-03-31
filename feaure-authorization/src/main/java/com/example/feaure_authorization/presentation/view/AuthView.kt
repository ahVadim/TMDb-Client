package com.example.feaure_authorization.presentation.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface AuthView : MvpView {

    @AddToEndSingle
    fun setLoginButtonEnable(isEnable: Boolean)

    @AddToEndSingle
    fun setErrorState(errorState: AuthErrorState)

    @OneExecution
    fun showSuccessAuthorizationMessage()
}
