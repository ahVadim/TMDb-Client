package com.example.feaure_authorization.data

import com.example.feaure_authorization.data.dto.CreateSessionRequestDto
import com.example.feaure_authorization.data.dto.ValidateTokenRequestDto
import com.example.feaure_authorization.di.AuthScope
import com.example.feaure_authorization.network.AuthApi
import io.reactivex.Single
import javax.inject.Inject

@AuthScope
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {

    override fun getRequestToken(): Single<String> {
        return authApi.getRequestToken()
            .map { it.request_token }
    }

    override fun getValidatedRequestToken(
        login: String,
        password: String,
        requestToken: String
    ): Single<String> {
        return authApi.validateRequestTokenWithLogin(
            ValidateTokenRequestDto(
                username = login,
                password = password,
                request_token = requestToken
            )
        )
            .map { it.request_token }
    }

    override fun createSessionAndGetSessionId(requestToken: String): Single<String> {
        return authApi.createSession(CreateSessionRequestDto(requestToken))
            .map { it.session_id }
    }
}
