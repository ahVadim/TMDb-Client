package com.example.feature_pincode.di

import androidx.lifecycle.ViewModel
import com.example.core.di.viewmodel.ViewModelKey
import com.example.core.di.viewmodel.ViewModelModule
import com.example.feature_pincode.presentation.PincodeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class PincodeModule {

    @Binds
    @IntoMap
    @ViewModelKey(PincodeViewModel::class)
    abstract fun provideAuthViewModel(viewModel: PincodeViewModel): ViewModel
}
