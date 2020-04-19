package com.example.feature_movieslist.data

import com.example.core.domain.MovieEntity
import com.example.feature_movieslist.data.network.FavoritesApi
import com.example.feature_movieslist.mapper.MovieMapper
import io.reactivex.Single
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val favoritesApi: FavoritesApi,
    private val movieMapper: MovieMapper
) {

    fun getFavorites(accountId: Int): Single<List<MovieEntity>> {
        return favoritesApi.getFavorites(accountId)
            .map { it.results.map(movieMapper::map) }
    }
}
