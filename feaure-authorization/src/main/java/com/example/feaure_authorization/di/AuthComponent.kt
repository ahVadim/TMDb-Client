package com.example.feaure_authorization.di

import com.example.core.di.CoreComponent
import com.example.feaure_authorization.presentation.AuthFragment
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
