package com.example.feaure_authorization.di

import com.example.feaure_authorization.data.AuthRepository
import com.example.feaure_authorization.data.AuthRepositoryImpl
import com.example.feaure_authorization.network.AuthApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class AuthModule {

    @Module
    companion object {

        @Provides
        fun provideAuthApi(retrofit: Retrofit): AuthApi {
            return retrofit.create(AuthApi::class.java)
        }
    }

    @Binds
    abstract fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository
}
