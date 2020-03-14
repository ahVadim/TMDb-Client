package com.example.movieslistapp.presentation

import android.app.Application
import com.example.movieslistapp.di.ComponentManager

class MoviesListApp: Application() {

    override fun onCreate() {
        super.onCreate()
        ComponentManager.initAppComponent()
    }
}
