package com.example.feature_moviedetail.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule
@Module(includes = [AssistedInject_MovieDetailsModule::class])
abstract class MovieDetailsModule
