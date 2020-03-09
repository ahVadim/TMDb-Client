package com.example.movieslistapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieslistapp.R
import com.example.movieslistapp.presentation.authorization.view.AuthFragment

class MainSingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            openAuthScreen()
        }
    }

    private fun openAuthScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AuthFragment.newInstance())
            .commitAllowingStateLoss()
    }
}
