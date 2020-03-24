package com.example.movieslistapp.di

import com.example.core.di.CoreComponent
import com.example.movieslistapp.presentation.AppActivity
import dagger.Component

@Component(dependencies = [CoreComponent::class])
@AppScope
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(appActivity: AppActivity)
}
