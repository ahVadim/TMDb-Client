package com.example.core.network

import android.net.ConnectivityManager
import com.example.core.exceptions.NoInternetConnectionException
import okhttp3.Interceptor
import okhttp3.Response
import javax.net.ssl.HttpsURLConnection

class NetworkErrorInterceptor(private val connectivityManager: ConnectivityManager?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasNetworkConnection()) {
            throw NoInternetConnectionException()
        }
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.code != HttpsURLConnection.HTTP_OK) {
            //todo
        }
        return response
    }

    private fun hasNetworkConnection(): Boolean {
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
