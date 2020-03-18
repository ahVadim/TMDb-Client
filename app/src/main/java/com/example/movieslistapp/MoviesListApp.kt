package com.example.movieslistapp

import android.app.Application
import com.example.core.di.CoreComponentHolder

class MoviesListApp: Application() {

    override fun onCreate() {
        super.onCreate()
        CoreComponentHolder.setupCoreComponent(this)
    }
}
