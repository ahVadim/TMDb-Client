package com.example.core.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.core.util.ResourceUtil
import dagger.Module
import dagger.Provides

@Module
class SystemModule {

    @Provides
    @AppScope
    fun provideConnectivityManager(context: Context): ConnectivityManager? {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    }

    @Provides
    @AppScope
    fun provideResourceUtil(context: Context): ResourceUtil {
        return ResourceUtil(context)
    }
}
