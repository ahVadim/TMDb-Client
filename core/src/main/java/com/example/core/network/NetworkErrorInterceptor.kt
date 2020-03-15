package com.example.core.network

import android.net.ConnectivityManager
import com.example.core.exceptions.AuthException
import com.example.core.exceptions.NoInternetConnectionException
import com.example.core.exceptions.ServerErrorException
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import java.nio.charset.Charset
import javax.net.ssl.HttpsURLConnection

class NetworkErrorInterceptor(private val connectivityManager: ConnectivityManager?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasNetworkConnection()) {
            throw NoInternetConnectionException()
        }
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code != HttpsURLConnection.HTTP_OK) {
            val errorMessage = getErrorMessage(response)
            throw when (response.code) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> AuthException(errorMessage)
                else -> ServerErrorException(errorMessage)
            }
        }
        return response
    }

    private fun hasNetworkConnection(): Boolean {
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun getErrorMessage(response: Response): String {
        return try {
            val source = response.body?.source()
            source?.request(Long.MAX_VALUE)
            val buffer = source?.buffer
            val charset = response.body?.contentType()?.charset()
                ?: Charset.forName("UTF-8")
            val jsonString = buffer?.clone()?.readString(charset).orEmpty()
            val jsonObj = JSONObject(jsonString)
            jsonObj.getString("status_message").orEmpty()
        } catch (e: Exception) {
            ""
        }
    }
}
