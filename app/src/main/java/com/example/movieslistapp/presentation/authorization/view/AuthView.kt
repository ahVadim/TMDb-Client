package com.example.movieslistapp.presentation.authorization.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface AuthView : MvpView {

    @AddToEndSingle
    fun setLoginButtonEnable(isEnable: Boolean)

    @AddToEndSingle
    fun showError(error: String?)
}
