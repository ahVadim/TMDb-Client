package com.example.movieslistapp.data

import io.reactivex.Completable

interface AuthRepository {
    fun authorize(login: String, password: String): Completable
}
