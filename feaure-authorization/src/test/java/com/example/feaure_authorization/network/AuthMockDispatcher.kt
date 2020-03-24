package com.example.feaure_authorization.network

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import javax.net.ssl.HttpsURLConnection

class AuthMockDispatcher : Dispatcher() {

    companion object {
        private const val REQUEST_TOKEN = "t762dlskfnl35"
        const val PROPER_LOGIN = "proper_login"
        const val PROPER_PASSWORD = "proper_password"
        const val SESSION_ID = "a49b2d62ffd9bfab0c59"
    }

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {

            "/authentication/token/new" -> MockResponse().setResponseCode(200)
                .setBody("{\"success\":true,\"expires_at\":\"2020-03-24 22:04:20 UTC\",\"request_token\":\"$REQUEST_TOKEN\"}")

            "/authentication/token/validate_with_login" -> getResponseForValidateToken(request.body.readUtf8())

            "/authentication/session/new" -> getResponseForSessionNew(request.body.readUtf8())

            else -> MockResponse().setResponseCode(HttpsURLConnection.HTTP_NOT_FOUND)
        }
    }

    private fun getResponseForValidateToken(body: String): MockResponse {
        return if (body == "{\"username\":\"$PROPER_LOGIN\",\"password\":\"$PROPER_PASSWORD\",\"request_token\":\"$REQUEST_TOKEN\"}") {
            MockResponse().setResponseCode(HttpsURLConnection.HTTP_OK)
                .setBody("{\"success\":true,\"expires_at\":\"2020-03-24 22:04:20 UTC\",\"request_token\":\"$REQUEST_TOKEN\"}")
        } else {
            MockResponse().setResponseCode(HttpsURLConnection.HTTP_UNAUTHORIZED)
        }
    }

    private fun getResponseForSessionNew(body: String): MockResponse {
        return if (body == "{\"request_token\":\"$REQUEST_TOKEN\"}") {
            MockResponse().setResponseCode(HttpsURLConnection.HTTP_OK)
                .setBody("{\"success\":true,\"session_id\":\"$SESSION_ID\"}")
        } else {
            MockResponse().setResponseCode(HttpsURLConnection.HTTP_UNAUTHORIZED)
        }
    }
}
