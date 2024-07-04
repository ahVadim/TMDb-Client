package com.example.feaure_authorization.domain

import com.example.core.data.account.AccountRepository
import com.example.core.data.session.SessionRepository
import com.example.core.di.FeatureScope
import com.example.core.prefs.UserPrefs
import javax.inject.Inject

@FeatureScope
class AuthInteractor @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val accountRepository: AccountRepository,
    private val userPrefs: UserPrefs
) {

    suspend fun authorize(login: String, password: String) {
        sessionRepository.refreshSessionId(login, password)
        val accountInfo = accountRepository.getAccountInfo()
        userPrefs.userLogin = login
        userPrefs.userPassword = password
        userPrefs.userName = accountInfo.name
        userPrefs.userId = accountInfo.id
    }
}
