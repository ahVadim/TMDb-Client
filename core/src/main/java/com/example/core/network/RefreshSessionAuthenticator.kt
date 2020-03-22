package com.example.core.network

import com.example.core.data.session.RefreshSessionRepository
import com.example.core.prefs.UserPrefs
import dagger.Lazy
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class RefreshSessionAuthenticator @Inject constructor(
    private val refreshSessionRepository: Lazy<RefreshSessionRepository>,
    private val userPrefs: UserPrefs
) : Authenticator {

    companion object {
        private const val SESSION_ID_QUERY_NAME = "session_id"
    }

    @Synchronized
    override fun authenticate(route: Route?, response: Response): Request? {
        val currentAccessToken = userPrefs.sessionId
        val requestAccessToken = response.request.url.queryParameter(SESSION_ID_QUERY_NAME)
        return when {

            currentAccessToken.isNullOrBlank() || requestAccessToken.isNullOrBlank() -> {
                null
            }

            currentAccessToken == requestAccessToken -> {
                val newSessionId = getNewSessionId() ?: return null
                buildRequestWithNewAccessToken(response, newSessionId)
            }

            else -> {
                buildRequestWithNewAccessToken(response, currentAccessToken)
            }
        }
    }

    private fun buildRequestWithNewAccessToken(
        response: Response,
        newSessionId: String
    ): Request {
        val newRequestUrl = response.request.url.newBuilder()
            .addQueryParameter(SESSION_ID_QUERY_NAME, newSessionId)
            .build()
        return response.request
            .newBuilder()
            .url(newRequestUrl)
            .build()
    }

    private fun getNewSessionId(): String? {
        return try {
            refreshSessionRepository.get()
                .refreshSessionId(
                    userPrefs.userLogin!!,
                    userPrefs.userPassword!!
                ).blockingGet()
        } catch (e: Exception) {
            null
        }
    }
}
