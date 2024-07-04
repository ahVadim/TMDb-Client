package com.example.feature_movieslist.data

import com.example.core.di.DispatcherIO
import com.example.core.domain.MovieEntity
import com.example.core.util.runCatchingCancellable
import com.example.feature_movieslist.data.db.FavoriteMoviesDao
import com.example.feature_movieslist.data.db.MovieDbMapper
import com.example.feature_movieslist.data.network.FavoritesApi
import com.example.feature_movieslist.mapper.MovieMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val favoritesApi: FavoritesApi,
    private val favoriteMoviesDao: FavoriteMoviesDao,
    private val movieMapper: MovieMapper,
    private val movieDbMapper: MovieDbMapper,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) {

    suspend fun getFavorites(accountId: Int): Flow<List<MovieEntity>> {
        getMoviesFromNetwork(accountId)
        return getMoviesFromDb()
    }

    private suspend fun getMoviesFromNetwork(accountId: Int) {
        withContext(dispatcherIO) {
            runCatchingCancellable(action = {
                val favorites = favoritesApi.getFavorites(accountId).results
                    .map(movieMapper::map)
                favoriteMoviesDao.clearAndInsertAll(favorites.map(movieDbMapper::map))
            })
        }
    }

    private fun getMoviesFromDb(): Flow<List<MovieEntity>> {
        return favoriteMoviesDao.getAll()
            .map { it.map(movieDbMapper::map) }
    }
}
