package com.example.core.data.session

import com.example.core.data.session.dto.CreateSessionRequestDto
import com.example.core.data.session.dto.DeleteSessionRequestDto
import com.example.core.data.session.dto.ValidateTokenRequestDto
import com.example.core.di.AppScope
import com.example.core.di.DispatcherIO
import com.example.core.network.api.SessionApi
import com.example.core.prefs.UserPrefs
import io.reactivex.Completable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
class SessionRepository @Inject constructor(
    private val sessionApi: SessionApi,
    private val userPrefs: UserPrefs,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) {

    suspend fun refreshSessionId(login: String, password: String): String {
        return withContext(dispatcherIO) {
            val requestToken = sessionApi.getRequestToken().requestToken

            val validateTokenRequest = ValidateTokenRequestDto(
                username = login,
                password = password,
                requestToken = requestToken
            )
            val validatedRequestToken =
                sessionApi.validateRequestTokenWithLogin(validateTokenRequest).requestToken

            val createSessionRequest = CreateSessionRequestDto(validatedRequestToken)
            val sessionId = sessionApi.createSession(createSessionRequest).sessionId
            userPrefs.sessionId = sessionId
            return@withContext sessionId
        }
    }

    fun deleteSession(): Completable {
        val sessionId = userPrefs.sessionId ?: return Completable.complete()
        return sessionApi.deleteSession(DeleteSessionRequestDto(sessionId))
            .doOnComplete { userPrefs.deleteAllPrefs() }
    }
}
