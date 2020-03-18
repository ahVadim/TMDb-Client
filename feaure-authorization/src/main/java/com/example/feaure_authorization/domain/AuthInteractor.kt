package com.example.feaure_authorization.domain

import com.example.feaure_authorization.data.AuthRepository
import com.example.feaure_authorization.di.AuthScope
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
