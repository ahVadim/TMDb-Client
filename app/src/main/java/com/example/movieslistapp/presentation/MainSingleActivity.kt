package com.example.movieslistapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieslistapp.R
import com.example.movieslistapp.presentation.authorization.view.AuthFragment

class MainSingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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
