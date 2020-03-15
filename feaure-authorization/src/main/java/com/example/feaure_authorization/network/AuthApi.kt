package com.example.feaure_authorization.network

import com.example.feaure_authorization.data.dto.RequestTokenResponseDto
import io.reactivex.Single
import retrofit2.http.GET

interface AuthApi {

    @GET("authentication/token/new")
    fun getRequestToken(): Single<RequestTokenResponseDto>
}
