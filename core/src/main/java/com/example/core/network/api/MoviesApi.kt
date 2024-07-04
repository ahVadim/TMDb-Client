package com.example.core.network.api

import com.example.core.data.movies.dto.MovieStateResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {
    @GET("movie/{movie_id}/account_states")
    suspend fun getMovieAccountStates(
        @Path("movie_id") movieId: Int
    ): MovieStateResponseDto
}
