package com.example.feature_movieslist.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieDb(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "poster_url") val posterUrl: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "origin_title") val originTitle: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "genre") val genre: String,
    @ColumnInfo(name = "rating") val rating: Double,
    @ColumnInfo(name = "rating_count") val ratingCount: Int,
    @ColumnInfo(name = "duration") val duration: String?
)
