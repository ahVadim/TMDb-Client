package com.example.feaure_authorization.data

import io.reactivex.Completable

interface AuthRepository {
    fun authorize(login: String, password: String): Completable
}
