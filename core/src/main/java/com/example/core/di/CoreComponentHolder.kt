package com.example.core.di

import android.content.Context

object CoreComponentHolder {

    lateinit var coreComponent: CoreComponent

    fun setupCoreComponent(context: Context) {
        coreComponent = DaggerCoreComponent.factory().create(context)
    }
}
