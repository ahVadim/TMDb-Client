package com.example.movieslistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.core.di.CoreComponentHolder
import com.example.feature_mainscreen.SessionMockFragment
import com.example.feaure_authorization.presentation.view.AuthFragment

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userPrefs = CoreComponentHolder.coreComponent.provideUserPrefs()
        if (userPrefs.sessionId != null) {
            openMainScreen()
        } else {
            openAuthScreen()
        }
    }

    private fun openAuthScreen() {
        supportFragmentManager.commit(true) {
            replace(R.id.fragment_container, AuthFragment.newInstance())
        }
    }

    private fun openMainScreen() {
        supportFragmentManager.commit(true) {
            replace(R.id.fragment_container, SessionMockFragment.newInstance())
        }
    }
}
