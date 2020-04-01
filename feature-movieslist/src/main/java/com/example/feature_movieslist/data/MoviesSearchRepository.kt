package com.example.feature_movieslist.data

import com.example.feature_movieslist.domain.MovieEntity
import com.example.feature_movieslist.mapper.MovieMapper
import com.example.feature_movieslist.network.SearchApi
import io.reactivex.Single
import javax.inject.Inject

class MoviesSearchRepository @Inject constructor(
    private val searchApi: SearchApi,
    private val movieMapper: MovieMapper
) {

    fun searchMovie(query: String): Single<List<MovieEntity>> {
        return searchApi.searchMovie(query)
            .map { it.results.map(movieMapper::map) }
    }
}
