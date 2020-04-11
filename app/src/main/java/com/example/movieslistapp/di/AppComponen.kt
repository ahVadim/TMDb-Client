package com.example.movieslistapp.di

import com.example.core.di.CoreComponent
import com.example.movieslistapp.presentation.AppActivity
import com.example.movieslistapp.presentation.splash.SplashFragment
import dagger.Component

@Component(dependencies = [CoreComponent::class], modules = [SplashModule::class])
@MainActivityScope
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(appActivity: AppActivity)

    fun inject(fragment: SplashFragment)
}
