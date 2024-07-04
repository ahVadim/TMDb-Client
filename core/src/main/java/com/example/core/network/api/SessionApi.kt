package com.example.core.network.api

import com.example.core.data.session.dto.CreateSessionRequestDto
import com.example.core.data.session.dto.CreateSessionResponseDto
import com.example.core.data.session.dto.DeleteSessionRequestDto
import com.example.core.data.session.dto.RequestTokenResponseDto
import com.example.core.data.session.dto.ValidateTokenRequestDto
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface SessionApi {

    @GET("authentication/token/new")
    suspend fun getRequestToken(): RequestTokenResponseDto

    @POST("authentication/token/validate_with_login")
    suspend fun validateRequestTokenWithLogin(@Body request: ValidateTokenRequestDto): RequestTokenResponseDto

    @POST("authentication/session/new")
    suspend fun createSession(@Body request: CreateSessionRequestDto): CreateSessionResponseDto

    @HTTP(method = "DELETE", path = "authentication/session", hasBody = true)
    fun deleteSession(@Body request: DeleteSessionRequestDto): Completable
}
