package com.example.feaure_authorization.network

import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.core.network.NetworkErrorInterceptor
import com.example.core.network.refreshsession.SessionApi
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object SessionApiFactory {
    fun getSessionApi(baseUrl: HttpUrl, isNetworkAvailable: Boolean): SessionApi {
        val networkInfoMock = mock<NetworkInfo> {
            on { isConnected } doReturn isNetworkAvailable
        }
        val connectivityManagerMock = mock<ConnectivityManager> {
            on { activeNetworkInfo } doReturn networkInfoMock
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(NetworkErrorInterceptor(connectivityManagerMock))
            .build()
        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()
        return retrofit.create(SessionApi::class.java)
    }
}
