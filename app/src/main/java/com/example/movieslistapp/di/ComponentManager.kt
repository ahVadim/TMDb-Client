package com.example.movieslistapp.di

import com.example.movieslistapp.di.auth.AuthComponent

object ComponentManager {

    private val componentMap = ComponentMap()
    lateinit var appComponent: AppComponent

    fun getOrBuildAppComponent(): AppComponent {

        if (this::appComponent.isInitialized) {
            return appComponent
        }

        return DaggerAppComponent.factory().create().also {
            appComponent = it
        }
    }

    fun getAuthComponent(): AuthComponent {
        return componentMap.getOrCreate { appComponent.plusAuthComponent() }
    }

    fun removeAuthComponent() {
        componentMap.release<AuthComponent>()
    }
}

internal class ComponentMap {

    val map = hashMapOf<String, Any>()

    inline fun <reified T : Any> getOrCreate(createAction: () -> T): T {
        val component = map[T::class.java.name] as? T
        return component ?: createAction().also { map[T::class.java.name] = it }
    }

    inline fun <reified T : Any> release() {
        map.remove(T::class.java.name)
    }
}
