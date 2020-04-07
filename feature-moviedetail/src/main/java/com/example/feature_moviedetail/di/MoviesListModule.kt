package com.example.feature_moviedetail.di

import androidx.lifecycle.ViewModel
import com.example.core.di.viewmodel.ViewModelKey
import com.example.core.di.viewmodel.ViewModelModule
import com.example.feature_moviedetail.presentation.MovieDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class MovieDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun provideAuthViewModel(viewModel: MovieDetailsViewModel): ViewModel
}
