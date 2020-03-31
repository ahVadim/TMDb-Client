package com.example.movieslistapp.presentation

import android.os.Bundle
import com.example.core.di.CoreComponentHolder
import com.example.movieslistapp.R
import com.example.movieslistapp.di.DaggerAppComponent
import moxy.MvpAppCompatActivity

class AppActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        DaggerAppComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
