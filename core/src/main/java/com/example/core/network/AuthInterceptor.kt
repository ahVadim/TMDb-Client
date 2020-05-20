package com.example.core.network

import com.example.core.BuildConfig
import com.example.core.prefs.UserPrefs
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val userPrefs: UserPrefs
) : Interceptor {

    companion object {
        const val API_TOKEN_QUERY_FIELD_NAME = "api_key"
        const val SESSION_ID_QUERY_FIELD_NAME = "session_id"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        val newUrlBuilder = originalRequest.url.newBuilder()
        newUrlBuilder.addQueryParameter(API_TOKEN_QUERY_FIELD_NAME, BuildConfig.API_TOKEN)
        val sessionId = userPrefs.sessionId
        if (!sessionId.isNullOrBlank()) {
            newUrlBuilder.addQueryParameter(SESSION_ID_QUERY_FIELD_NAME, sessionId)
        }

        val newRequest = originalRequest.newBuilder()
            .url(newUrlBuilder.build())
            .build()

        return chain.proceed(newRequest)
    }
}
