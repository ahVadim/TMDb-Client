package com.example.movieslistapp.presentation.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.CoreComponentHolder
import com.example.core.presentation.BaseFragment
import com.example.core.util.observe
import com.example.movieslistapp.R
import com.example.movieslistapp.di.DaggerAppComponent
import javax.inject.Inject

class SplashFragment : BaseFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val splashViewModel: SplashViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        DaggerAppComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(splashViewModel.eventsQueue, ::onEvent)
    }
}
