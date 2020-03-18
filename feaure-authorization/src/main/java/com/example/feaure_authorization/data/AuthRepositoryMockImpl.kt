package com.example.feaure_authorization.data

import com.example.core.exceptions.AuthException
import com.example.feaure_authorization.di.AuthScope
import io.reactivex.Completable
import javax.inject.Inject
import kotlin.random.Random

@AuthScope
class AuthRepositoryMockImpl @Inject constructor() : AuthRepository {

    override fun authorize(login: String, password: String): Completable {
        return if (Random.nextBoolean()) {
            val exception = if (Random.nextBoolean()) {
                AuthException()
            } else {
                Exception("Network is Not Available")
            }
            Completable.error(exception)
        } else {
            Completable.complete()
        }
    }
}
