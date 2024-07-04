package com.example.feature_movieslist.data

import com.example.core.di.DispatcherIO
import com.example.core.domain.MovieEntity
import com.example.feature_movieslist.data.network.SearchApi
import com.example.feature_movieslist.mapper.MovieMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesSearchRepository @Inject constructor(
    private val searchApi: SearchApi,
    private val movieMapper: MovieMapper,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) {

    suspend fun searchMovie(query: String): List<MovieEntity> {
        return withContext(dispatcherIO) {
            searchApi.searchMovie(query).results
                .map(movieMapper::map)
        }
    }
}
