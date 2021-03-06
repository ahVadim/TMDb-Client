package com.example.core.di

import android.net.ConnectivityManager
import com.example.core.BuildConfig
import com.example.core.network.AuthInterceptor
import com.example.core.network.NetworkErrorInterceptor
import com.example.core.network.RefreshSessionAuthenticator
import com.example.core.network.api.AccountApi
import com.example.core.network.api.MoviesApi
import com.example.core.network.api.SessionApi
import com.example.core.prefs.UserPrefs
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    @AppScope
    fun provideOkhttpClient(
        connectivityManager: ConnectivityManager?,
        refreshSessionAuthenticator: RefreshSessionAuthenticator,
        userPrefs: UserPrefs
    ): OkHttpClient {
        val certificatePinner = CertificatePinner.Builder()
            .add(BuildConfig.HOST_PATTERN, BuildConfig.SSL_KEY)
            .build()
        return OkHttpClient.Builder().apply {
            if (!BuildConfig.DEBUG) {
                certificatePinner(certificatePinner)
            }
            addInterceptor(AuthInterceptor(userPrefs))
            addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                } else {
                    setLevel(HttpLoggingInterceptor.Level.NONE)
                }
            })
            addInterceptor(NetworkErrorInterceptor(connectivityManager))
            authenticator(refreshSessionAuthenticator)
        }
            .build()
    }

    @Provides
    @AppScope
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @AppScope
    fun provideSessionApi(retrofit: Retrofit): SessionApi {
        return retrofit.create(SessionApi::class.java)
    }

    @Provides
    @AppScope
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    @AppScope
    fun provideAccountApi(retrofit: Retrofit): AccountApi {
        return retrofit.create(AccountApi::class.java)
    }
}
