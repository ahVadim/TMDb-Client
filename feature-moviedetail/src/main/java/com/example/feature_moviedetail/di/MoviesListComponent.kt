package com.example.feature_moviedetail.di

import com.example.core.di.CoreComponent
import com.example.feature_moviedetail.presentation.MovieDetailsFragment
import dagger.Component

@Component(dependencies = [CoreComponent::class], modules = [MovieDetailsModule::class])
@MovieDetailsScope
interface MoviesDetailsComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): MoviesDetailsComponent
    }

    fun inject(fragment: MovieDetailsFragment)
}
