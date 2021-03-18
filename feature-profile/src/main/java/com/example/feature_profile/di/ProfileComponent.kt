package com.example.feature_profile.di

import com.example.core.di.CoreComponent
import com.example.core.di.FeatureScope
import com.example.feature_profile.presentation.ProfileFragment
import dagger.Component

@Component(dependencies = [CoreComponent::class])
@FeatureScope
interface ProfileComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): ProfileComponent
    }

    fun inject(fragment: ProfileFragment)
}
