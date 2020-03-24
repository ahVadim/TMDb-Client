package com.example.movieslistapp.presentation

import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution

interface AppView : MvpView {

    @OneExecution
    fun openMainScreen()

    @OneExecution
    fun openAuthScreen()
}
