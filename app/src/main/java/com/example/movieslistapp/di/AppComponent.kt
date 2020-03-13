package com.example.movieslistapp.di

import com.example.movieslistapp.di.auth.AuthComponent
import dagger.Component

@Component
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(): AppComponent
    }

    fun plusAuthComponent(): AuthComponent
}
