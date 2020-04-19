package com.example.feature_movieslist.data

import com.example.core.domain.MovieEntity
import com.example.feature_movieslist.data.db.FavoriteMoviesDao
import com.example.feature_movieslist.data.db.MovieDbMapper
import com.example.feature_movieslist.data.network.FavoritesApi
import com.example.feature_movieslist.mapper.MovieMapper
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val favoritesApi: FavoritesApi,
    private val favoriteMoviesDao: FavoriteMoviesDao,
    private val movieMapper: MovieMapper,
    private val movieDbMapper: MovieDbMapper
) {

    fun getFavorites(accountId: Int): Observable<List<MovieEntity>> {
        return getMoviesFromNetwork(accountId).andThen(getMoviesFromDb())
    }

    private fun getMoviesFromNetwork(accountId: Int): Completable {
        return favoritesApi.getFavorites(accountId)
            .map { it.results.map(movieMapper::map) }
            .doOnSuccess { favoriteMoviesDao.insertAll(it.map(movieDbMapper::map)) }
            .ignoreElement()
            .onErrorComplete()
    }

    private fun getMoviesFromDb(): Observable<List<MovieEntity>> {
        return favoriteMoviesDao.getAll()
            .map { it.map(movieDbMapper::map) }
    }
}
