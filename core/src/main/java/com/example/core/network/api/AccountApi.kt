package com.example.core.network.api

import com.example.core.data.account.dto.GetAccountResponseDto
import com.example.core.data.account.dto.SetIsFavoriteRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountApi {

    @GET("account")
    suspend fun getAccountDetails(): GetAccountResponseDto

    @POST("account/{account_id}/favorite")
    suspend fun setIsFavorite(
        @Path("account_id") userId: Int,
        @Body request: SetIsFavoriteRequestDto
    )
}
