package com.example.feature_movieslist.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMoviesDao {

    @Transaction
    suspend fun clearAndInsertAll(movies: List<MovieDb>) {
        clear()
        insertAll(movies)
    }

    @Query("DELETE FROM movie")
    suspend fun clear()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieDb>)

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<MovieDb>>
}
