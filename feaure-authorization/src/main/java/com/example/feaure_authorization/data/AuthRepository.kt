package com.example.feaure_authorization.data

import io.reactivex.Completable
import io.reactivex.Single

interface AuthRepository {

    fun authorize(login: String, password: String): Completable

    fun getRequestToken(): Single<String>
}
