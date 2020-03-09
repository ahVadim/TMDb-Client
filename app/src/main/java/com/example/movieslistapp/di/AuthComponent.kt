package com.example.movieslistapp.di

import com.example.movieslistapp.data.AuthRepository
import com.example.movieslistapp.data.AuthRepositoryMockImpl
import com.example.movieslistapp.presentation.authorization.presenter.AuthPresenter
import com.example.movieslistapp.presentation.authorization.view.AuthFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import javax.inject.Scope

@Subcomponent(modules = [AuthModule::class])
@AuthScope
interface AuthComponent {

    fun inject(fragment: AuthFragment)
}

@Module
abstract class AuthModule {
    @Binds
    abstract fun bindAuthRepository(repository: AuthRepositoryMockImpl): AuthRepository
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthScope
