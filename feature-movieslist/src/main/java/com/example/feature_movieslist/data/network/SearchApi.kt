package com.example.feature_movieslist.data.network

import com.example.feature_movieslist.data.network.dto.MoviesResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query: String): MoviesResponseDto
}
