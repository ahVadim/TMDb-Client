package com.example.movieslistapp.presentation.authorization.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface AuthView : MvpView {

    @AddToEndSingle
    fun setLoginButtonEnable(isEnable: Boolean)

    @AddToEndSingle
    fun showTryLaterError()

    @AddToEndSingle
    fun showIncorrectDataError()

    @AddToEndSingle
    fun hideError()

    @OneExecution
    fun showSuccessAuthorizationMessage()
}
