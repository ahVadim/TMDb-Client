package com.example.movieslistapp.presentation

import android.os.Bundle
import androidx.fragment.app.commit
import com.example.core.di.CoreComponentHolder
import com.example.feature_profile.presentation.ProfileFragment
import com.example.feaure_authorization.presentation.view.AuthFragment
import com.example.movieslistapp.R
import com.example.movieslistapp.di.DaggerAppComponent
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class AppActivity : MvpAppCompatActivity(), AppView {

    @Inject
    lateinit var presenterProvider: Provider<AppPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        DaggerAppComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun openAuthScreen() {
        supportFragmentManager.commit(true) {
            replace(R.id.fragment_container, AuthFragment.newInstance())
        }
    }

    override fun openMainScreen() {
        supportFragmentManager.commit(true) {
            replace(R.id.fragment_container, ProfileFragment.newInstance())
        }
    }
}
