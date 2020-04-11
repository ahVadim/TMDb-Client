package com.example.core.network.api

import com.example.core.data.account.dto.GetAccountResponseDto
import com.example.core.data.movies.dto.MovieStateResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AccountApi {
    @GET("account")
    fun getAccountDetails(): Single<GetAccountResponseDto>
}
