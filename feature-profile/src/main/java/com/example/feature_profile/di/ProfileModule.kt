package com.example.feature_profile.di

import androidx.lifecycle.ViewModel
import com.example.core.di.viewmodel.ViewModelKey
import com.example.core.di.viewmodel.ViewModelModule
import com.example.feature_profile.presentation.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class ProfileModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun provideAuthViewModel(viewModel: ProfileViewModel): ViewModel
}
