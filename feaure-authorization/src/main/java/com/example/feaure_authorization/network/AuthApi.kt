package com.example.feaure_authorization.network

import com.example.feaure_authorization.data.dto.CreateSessionRequestDto
import com.example.feaure_authorization.data.dto.CreateSessionResponseDto
import com.example.feaure_authorization.data.dto.RequestTokenResponseDto
import com.example.feaure_authorization.data.dto.ValidateTokenRequestDto
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @GET("authentication/token/new")
    fun getRequestToken(): Single<RequestTokenResponseDto>

    @POST("authentication/token/validate_with_login")
    fun validateRequestTokenWithLogin(@Body request: ValidateTokenRequestDto): Single<RequestTokenResponseDto>

    @POST("authentication/session/new")
    fun createSession(@Body request: CreateSessionRequestDto): Single<CreateSessionResponseDto>
}
