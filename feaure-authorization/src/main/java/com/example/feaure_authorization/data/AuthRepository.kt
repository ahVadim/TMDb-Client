package com.example.feaure_authorization.data

import io.reactivex.Single

interface AuthRepository {

    fun getRequestToken(): Single<String>

    fun getValidatedRequestToken(
        login: String,
        password: String,
        requestToken: String
    ): Single<String>

    fun createSessionAndGetSessionId(requestToken: String): Single<String>
}
