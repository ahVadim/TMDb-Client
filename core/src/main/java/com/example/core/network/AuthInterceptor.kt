package com.example.core.network

import com.example.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    companion object {
        private const val API_TOKEN_QUERY_FIELD_NAME = "api_key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        val newUrl = originalRequest.url.newBuilder()
            .addQueryParameter(API_TOKEN_QUERY_FIELD_NAME, BuildConfig.API_TOKEN)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
