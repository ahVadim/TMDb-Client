package com.example.feaure_authorization.di

import com.example.feaure_authorization.data.AuthRepository
import com.example.feaure_authorization.data.AuthRepositoryMockImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AuthModule {

    @Binds
    abstract fun bindAuthRepository(repository: AuthRepositoryMockImpl): AuthRepository
}
