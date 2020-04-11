package com.example.core.data.movies

import com.example.core.network.api.MoviesApi
import com.example.core.prefs.UserPrefs
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi
) {

    fun isMovieFavorite(movieId: Int): Single<Boolean> {
        return moviesApi.getMovieAccountStates(movieId)
            .map { it.isFavorite }
    }
}
