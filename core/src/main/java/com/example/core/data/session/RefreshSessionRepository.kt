package com.example.core.data.session

import com.example.core.data.session.dto.CreateSessionRequestDto
import com.example.core.data.session.dto.ValidateTokenRequestDto
import com.example.core.network.refreshsession.SessionApi
import com.example.core.prefs.UserPrefs
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RefreshSessionRepository @Inject constructor(
    private val sessionApi: SessionApi,
    private val userPrefs: UserPrefs
) {

    fun refreshSessionId(login: String, password: String): Single<String> {
        return sessionApi.getRequestToken()
            .map { it.request_token }
            .flatMap { requestToken ->
                sessionApi.validateRequestTokenWithLogin(
                    ValidateTokenRequestDto(
                        username = login,
                        password = password,
                        request_token = requestToken
                    )
                )
            }
            .map { it.request_token }
            .flatMap { validatedRequestToken ->
                sessionApi.createSession(
                    CreateSessionRequestDto(validatedRequestToken)
                )
            }
            .map { it.session_id }
            .doOnSuccess { sessionId ->
                userPrefs.sessionId = sessionId
            }
    }
}
