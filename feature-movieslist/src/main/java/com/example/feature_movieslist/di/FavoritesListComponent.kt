package com.example.feature_movieslist.di

import com.example.core.di.CoreComponent
import com.example.core.di.FeatureScope
import com.example.feature_movieslist.presentation.favorites.FavoritesListFragment
import dagger.Component

@Component(dependencies = [CoreComponent::class], modules = [FavoritesListModule::class])
@FeatureScope
interface FavoritesListComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): FavoritesListComponent
    }

    fun inject(fragment: FavoritesListFragment)
}
