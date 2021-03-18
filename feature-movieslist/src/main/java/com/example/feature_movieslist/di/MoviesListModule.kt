package com.example.feature_movieslist.di

import com.example.core.di.FeatureScope
import com.example.feature_movieslist.data.network.SearchApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class MoviesListModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FeatureScope
        fun provideSessionApi(retrofit: Retrofit): SearchApi {
            return retrofit.create(SearchApi::class.java)
        }
    }
}
