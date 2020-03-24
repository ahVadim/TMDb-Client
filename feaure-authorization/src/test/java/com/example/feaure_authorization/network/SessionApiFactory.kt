package com.example.feaure_authorization.network

import com.example.core.network.refreshsession.SessionApi
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object SessionApiFactory {
    fun getSessionApi(baseUrl: HttpUrl): SessionApi {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()
        return retrofit.create(SessionApi::class.java)
    }
}
