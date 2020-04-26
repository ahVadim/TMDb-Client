package com.example.feature_movieslist.mapper

import com.example.core.consts.IMAGE_BASE_URL
import com.example.core.consts.MIN_RATING_FOR_WORTH_WATCHING
import com.example.core.domain.MovieEntity
import com.example.feature_movieslist.data.network.dto.MovieDto
import javax.inject.Inject

class MovieMapper @Inject constructor() {
    fun map(movieDto: MovieDto): MovieEntity {
        return MovieEntity(
            id = movieDto.id,
            posterUrl = IMAGE_BASE_URL + movieDto.posterPath,
            title = movieDto.title,
            originTitle = movieDto.originalTitle,
            description = movieDto.overview,
            genre = movieDto.genreIds.toString(), // todo load genres
            rating = movieDto.voteAverage,
            ratingCount = movieDto.voteCount,
            duration = null, // todo load duration
            isWorthWatching = movieDto.voteAverage > MIN_RATING_FOR_WORTH_WATCHING
        )
    }
}
