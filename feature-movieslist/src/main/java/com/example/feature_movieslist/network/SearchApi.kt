package com.example.feature_movieslist.network

import com.example.feature_movieslist.data.dto.MoviesResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/movie")
    fun searchMovie(@Query("query") query: String): Single<MoviesResponseDto>
}
