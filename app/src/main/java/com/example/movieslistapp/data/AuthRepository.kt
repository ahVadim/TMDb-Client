package com.example.movieslistapp.data

import io.reactivex.Single

interface AuthRepository {
    fun authorize(login: String, password: String): Single<Boolean>
}
