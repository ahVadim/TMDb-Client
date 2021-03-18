package com.example.feature_pincode.di

import com.example.core.di.CoreComponent
import com.example.core.di.FeatureScope
import com.example.feature_pincode.presentation.PincodeFragment
import dagger.Component

@Component(dependencies = [CoreComponent::class])
@FeatureScope
interface PincodeComponent {

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent): PincodeComponent
    }

    fun inject(fragment: PincodeFragment)
}
