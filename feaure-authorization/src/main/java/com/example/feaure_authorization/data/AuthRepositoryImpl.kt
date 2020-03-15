package com.example.feaure_authorization.data

import android.util.Log
import com.example.feaure_authorization.di.AuthScope
import com.example.feaure_authorization.network.AuthApi
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

@AuthScope
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {

    override fun authorize(login: String, password: String): Completable {
        return getRequestToken().ignoreElement()
    }

    override fun getRequestToken(): Single<String> {
        return authApi.getRequestToken()
            .map {
                Log.d("avd", it.toString())
                it.request_token
            }
    }
}
