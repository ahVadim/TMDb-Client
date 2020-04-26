package com.example.feature_movieslist.data.db

import com.example.core.domain.MovieEntity
import javax.inject.Inject

class MovieDbMapper @Inject constructor() {

    fun map(movieEntity: MovieEntity): MovieDb {
        return MovieDb(
            id = movieEntity.id,
            posterUrl = movieEntity.posterUrl,
            title = movieEntity.title,
            originTitle = movieEntity.originTitle,
            description = movieEntity.description,
            genre = movieEntity.genre,
            rating = movieEntity.rating,
            ratingCount = movieEntity.ratingCount,
            duration = movieEntity.duration,
            isWorthWatching = movieEntity.isWorthWatching
        )
    }

    fun map(movieDb: MovieDb): MovieEntity {
        return MovieEntity(
            id = movieDb.id,
            posterUrl = movieDb.posterUrl,
            title = movieDb.title,
            originTitle = movieDb.originTitle,
            description = movieDb.description,
            genre = movieDb.genre,
            rating = movieDb.rating,
            ratingCount = movieDb.ratingCount,
            duration = movieDb.duration,
            isWorthWatching = movieDb.isWorthWatching
        )
    }
}
