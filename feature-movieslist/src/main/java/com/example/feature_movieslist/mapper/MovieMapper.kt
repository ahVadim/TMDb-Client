package com.example.feature_movieslist.mapper

import com.example.core.consts.IMAGE_BASE_URL
import com.example.feature_movieslist.data.dto.MovieDto
import com.example.feature_movieslist.domain.MovieEntity
import javax.inject.Inject

class MovieMapper @Inject constructor() {
    fun map(movieDto: MovieDto): MovieEntity {
        return MovieEntity(
            id = movieDto.id,
            posterUrl = IMAGE_BASE_URL + movieDto.poster_path,
            title = movieDto.title,
            subtitle = movieDto.original_title,
            genre = movieDto.genre_ids.toString(), // todo load genres
            rating = movieDto.vote_average,
            ratingCount = movieDto.vote_count,
            duration = null // todo load duration
        )
    }
}
