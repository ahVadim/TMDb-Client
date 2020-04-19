package com.example.feature_movieslist.di

import androidx.lifecycle.ViewModel
import com.example.core.di.FeatureScope
import com.example.core.di.viewmodel.ViewModelKey
import com.example.core.di.viewmodel.ViewModelModule
import com.example.feature_movieslist.network.FavoritesApi
import com.example.feature_movieslist.presentation.favorites.FavoritesListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [ViewModelModule::class])
abstract class FavoritesListModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FeatureScope
        fun provideFavoritesApi(retrofit: Retrofit): FavoritesApi {
            return retrofit.create(FavoritesApi::class.java)
        }
    }

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesListViewModel::class)
    abstract fun provideAuthViewModel(viewModel: FavoritesListViewModel): ViewModel
}
