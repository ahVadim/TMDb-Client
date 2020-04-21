package com.example.feature_movieslist.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_movieslist.data.db.MovieDb.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MovieDb(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "poster_url") val posterUrl: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "origin_title") val originTitle: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "genre") val genre: String,
    @ColumnInfo(name = "rating") val rating: Double,
    @ColumnInfo(name = "rating_count") val ratingCount: Int,
    @ColumnInfo(name = "duration") val duration: String?,
    @ColumnInfo(name = NEW_FIELD_IS_WATCHED_NAME) val isWatched: Boolean = false
) {

    companion object {
        const val TABLE_NAME = "movie"
        const val NEW_FIELD_IS_WATCHED_NAME = "is_watched"
    }
}
