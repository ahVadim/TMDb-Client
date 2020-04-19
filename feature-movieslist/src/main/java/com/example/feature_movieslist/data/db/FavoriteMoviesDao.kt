package com.example.feature_movieslist.data.db

import androidx.room.Dao
import androidx.room.Insert
import io.reactivex.Completable

@Dao
interface FavoriteMoviesDao {

    @Insert
    fun insertAll(movies: List<MovieDb>): Completable
}
