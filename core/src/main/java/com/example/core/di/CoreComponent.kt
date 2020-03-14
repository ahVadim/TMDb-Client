package com.example.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component
interface CoreComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideContext(): Context
}
