package com.example.movieslistapp.domain.authorization

import com.example.movieslistapp.data.AuthRepository
import com.example.movieslistapp.di.auth.AuthScope
import io.reactivex.Completable
import javax.inject.Inject

@AuthScope
class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun authorize(login: String, password: String): Completable {
        return authRepository.authorize(login = login, password = password)
    }
}
