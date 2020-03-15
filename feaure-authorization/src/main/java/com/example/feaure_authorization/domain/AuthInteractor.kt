package com.example.feaure_authorization.domain

import com.example.core.data.session.RefreshSessionRepository
import com.example.feaure_authorization.di.AuthScope
import io.reactivex.Completable
import javax.inject.Inject

@AuthScope
class AuthInteractor @Inject constructor(
    private val sessionRepository: RefreshSessionRepository
) {

    fun authorize(login: String, password: String): Completable {
        return sessionRepository.refreshSessionId(login, password)
    }
}
