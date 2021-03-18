package com.example.core.di

import android.content.Context
import com.example.core.data.account.AccountRepository
import com.example.core.data.movies.MoviesRepository
import com.example.core.data.session.SessionRepository
import com.example.core.prefs.UserPrefs
import com.example.core.util.ResourceUtil
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@AppScope
@Component(modules = [NetworkModule::class, SystemModule::class, SecurePrefsModule::class])
interface CoreComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideContext(): Context

    fun provideResourceUtil(): ResourceUtil

    fun provideRetrofit(): Retrofit

    fun provideRefreshSessionRepository(): SessionRepository

    fun provideUserPrefs(): UserPrefs

    fun provideMoviesRepository(): MoviesRepository

    fun provideAccountRepository(): AccountRepository
}
