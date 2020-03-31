package com.example.feature_profile.di

import com.example.core.di.CoreComponent
import com.example.feature_profile.presentation.ProfileFragment
import dagger.Component

@Component(dependencies = [CoreComponent::class])
@ProfileScope
interface ProfileComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): ProfileComponent
    }

    fun inject(fragment: ProfileFragment)
}
