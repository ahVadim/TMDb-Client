package com.example.movieslistapp.presentation.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.di.CoreComponentHolder
import com.example.core.presentation.BaseFragment
import com.example.core.util.observe
import com.example.core.util.viewModelFromProvider
import com.example.movieslistapp.R
import com.example.movieslistapp.di.DaggerAppComponent
import javax.inject.Inject
import javax.inject.Provider

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    @Inject
    internal lateinit var viewModelProvider: Provider<SplashViewModel>
    private val splashViewModel: SplashViewModel by viewModelFromProvider { viewModelProvider }

    override fun onAttach(context: Context) {
        DaggerAppComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(splashViewModel.eventsQueue, ::onEvent)
    }
}
