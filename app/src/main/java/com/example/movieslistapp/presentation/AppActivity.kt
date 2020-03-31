package com.example.movieslistapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.core.di.CoreComponentHolder
import com.example.movieslistapp.R
import com.example.movieslistapp.di.DaggerAppComponent

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        DaggerAppComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
