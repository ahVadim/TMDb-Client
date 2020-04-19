package com.example.feature_movieslist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieDb::class], version = 1)
abstract class FavoriteMoviesDb : RoomDatabase() {

    abstract fun favoriteMoviesDao(): FavoriteMoviesDao
}
