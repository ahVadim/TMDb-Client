package com.example.core.data.movies

import com.example.core.di.DispatcherIO
import com.example.core.network.api.MoviesApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) {

    suspend fun isMovieFavorite(movieId: Int): Boolean {
        return withContext(dispatcherIO) {
            moviesApi.getMovieAccountStates(movieId).isFavorite
        }
    }
}
