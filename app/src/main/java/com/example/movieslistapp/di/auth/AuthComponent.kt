package com.example.movieslistapp.di.auth

import com.example.movieslistapp.presentation.authorization.view.AuthFragment
import dagger.Subcomponent

@Subcomponent(modules = [AuthModule::class])
@AuthScope
interface AuthComponent {

    fun inject(fragment: AuthFragment)
}
