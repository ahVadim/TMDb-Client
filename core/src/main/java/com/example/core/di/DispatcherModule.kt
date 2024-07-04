package com.example.core.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DispatcherIO

@Module
class DispatcherModule {
    @Provides
    @DispatcherIO
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO
}