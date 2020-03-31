package com.example.feaure_authorization.domain

import com.example.core.data.session.SessionRepository
import com.example.core.prefs.UserPrefs
import com.example.feaure_authorization.di.AuthScope
import io.reactivex.Completable
import javax.inject.Inject

@AuthScope
class AuthInteractor @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val userPrefs: UserPrefs
) {

    fun authorize(login: String, password: String): Completable {
        return sessionRepository.refreshSessionId(login, password)
            .ignoreElement()
            .doOnComplete {
                userPrefs.userLogin = login
                userPrefs.userPassword = password
            }
    }
}
