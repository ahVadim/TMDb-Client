package com.example.core.network.api

import com.example.core.data.account.dto.GetAccountResponseDto
import com.example.core.data.account.dto.SetIsFavoriteRequestDto
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountApi {

    @GET("account")
    fun getAccountDetails(): Single<GetAccountResponseDto>

    @POST("account/{account_id}/favorite")
    fun setIsFavorite(
        @Path("account_id") userId: Int,
        @Body request: SetIsFavoriteRequestDto
    ): Completable
}
