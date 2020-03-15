package com.example.movieslistapp

import android.app.Application
import com.example.core.di.CoreComponentHolder
import com.facebook.stetho.Stetho

class MoviesListApp: Application() {

    override fun onCreate() {
        super.onCreate()
        CoreComponentHolder.setupCoreComponent(this)
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}
