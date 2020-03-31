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

        private const val NEW_TOKEN_RESPONSE = """{"success":true,"expires_at":"2020-03-24 22:04:20 UTC","request_token":"$REQUEST_TOKEN"}"""
        private const val PROPER_VALIDATE_TOKEN_REQUEST = """{"username":"$PROPER_LOGIN","password":"$PROPER_PASSWORD","request_token":"$REQUEST_TOKEN"}"""
        private const val VALIDATE_TOKEN_RESPONSE = """{"success":true,"expires_at":"2020-03-24 22:04:20 UTC","request_token":"$REQUEST_TOKEN"}"""
        private const val PROPER_NEW_SESSION_REQUEST = """{"request_token":"$REQUEST_TOKEN"}"""
        private const val NEW_SESSION_RESPONSE = """{"success":true,"session_id":"$SESSION_ID"}"""
    }

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {

            "/authentication/token/new" -> MockResponse().setResponseCode(200)
                .setBody(NEW_TOKEN_RESPONSE)

            "/authentication/token/validate_with_login" -> getResponseForValidateToken(request.body.readUtf8())

            "/authentication/session/new" -> getResponseForSessionNew(request.body.readUtf8())

            else -> MockResponse().setResponseCode(HttpsURLConnection.HTTP_NOT_FOUND)
        }
    }

    private fun getResponseForValidateToken(body: String): MockResponse {
        return if (body == PROPER_VALIDATE_TOKEN_REQUEST) {
            MockResponse().setResponseCode(HttpsURLConnection.HTTP_OK)
                .setBody(VALIDATE_TOKEN_RESPONSE)
        } else {
            MockResponse().setResponseCode(HttpsURLConnection.HTTP_UNAUTHORIZED)
        }
    }

    private fun getResponseForSessionNew(body: String): MockResponse {
        return if (body == PROPER_NEW_SESSION_REQUEST) {
            MockResponse().setResponseCode(HttpsURLConnection.HTTP_OK)
                .setBody(NEW_SESSION_RESPONSE)
        } else {
            MockResponse().setResponseCode(HttpsURLConnection.HTTP_UNAUTHORIZED)
        }
    }
}
