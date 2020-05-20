package com.example.feature_movieslist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_movieslist.data.db.migrations.Migrations

@Database(entities = [MovieDb::class], version = Migrations.VERSION_3)
abstract class FavoriteMoviesDb : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "favorites-movies-db"
    }

    abstract fun favoriteMoviesDao(): FavoriteMoviesDao
}
