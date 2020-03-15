package com.example.core.network

import com.example.core.data.session.RefreshSessionRepository
import dagger.Lazy
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class RefreshSessionAuthenticator @Inject constructor(
    private val refreshSessionRepository: Lazy<RefreshSessionRepository>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return null
    }
}
