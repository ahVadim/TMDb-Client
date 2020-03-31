package com.example.core.network.refreshsession

import com.example.core.data.session.dto.CreateSessionRequestDto
import com.example.core.data.session.dto.CreateSessionResponseDto
import com.example.core.data.session.dto.DeleteSessionRequestDto
import com.example.core.data.session.dto.RequestTokenResponseDto
import com.example.core.data.session.dto.ValidateTokenRequestDto
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface SessionApi {

    @GET("authentication/token/new")
    fun getRequestToken(): Single<RequestTokenResponseDto>

    @POST("authentication/token/validate_with_login")
    fun validateRequestTokenWithLogin(@Body request: ValidateTokenRequestDto): Single<RequestTokenResponseDto>

    @POST("authentication/session/new")
    fun createSession(@Body request: CreateSessionRequestDto): Single<CreateSessionResponseDto>

    @DELETE("authentication/session")
    fun deleteSession(@Body request: DeleteSessionRequestDto): Completable
}
