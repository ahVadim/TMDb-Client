package com.example.movieslistapp.di.auth

import com.example.movieslistapp.data.AuthRepository
import com.example.movieslistapp.data.AuthRepositoryMockImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AuthModule {

    @Binds
    abstract fun bindAuthRepository(repository: AuthRepositoryMockImpl): AuthRepository
}
