package com.example.feature_movieslist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_movieslist.data.db.migrations.Migrations

@Database(entities = [MovieDb::class], version = Migrations.VERSION_2)
abstract class FavoriteMoviesDb : RoomDatabase() {

    abstract fun favoriteMoviesDao(): FavoriteMoviesDao
}
