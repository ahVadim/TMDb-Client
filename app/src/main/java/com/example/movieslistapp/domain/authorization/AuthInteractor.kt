package com.example.movieslistapp.domain.authorization

import com.example.movieslistapp.data.AuthRepository
import com.example.movieslistapp.di.AuthScope
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

@AuthScope
class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun authorize(login: String, password: String): Single<Boolean> {
        return authRepository.authorize(login = login, password = password)
    }
}
