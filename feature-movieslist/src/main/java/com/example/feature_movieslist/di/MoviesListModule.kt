package com.example.feature_movieslist.di

import androidx.lifecycle.ViewModel
import com.example.core.di.viewmodel.ViewModelKey
import com.example.core.di.viewmodel.ViewModelModule
import com.example.feature_movieslist.presentation.movies.MoviesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class MoviesListModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    abstract fun provideAuthViewModel(viewModel: MoviesListViewModel): ViewModel
}
