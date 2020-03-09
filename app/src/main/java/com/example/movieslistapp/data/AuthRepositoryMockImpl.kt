package com.example.movieslistapp.data

import com.example.movieslistapp.di.AuthScope
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject
import kotlin.random.Random

@AuthScope
class AuthRepositoryMockImpl @Inject constructor(): AuthRepository{
    override fun authorize(login: String, password: String): Single<Boolean> {
        return if (Random.nextBoolean()) {
            Single.error(Exception("Network is Not Available"))
        } else {
            Single.fromCallable { Random.nextBoolean() }
        }
    }
}
