package com.example.movieslistapp.di.auth

import com.example.core.di.CoreComponent
import com.example.movieslistapp.presentation.authorization.view.AuthFragment
import dagger.Component

@Component(dependencies = [CoreComponent::class], modules = [AuthModule::class])
@AuthScope
interface AuthComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): AuthComponent
    }

    fun inject(fragment: AuthFragment)
}
