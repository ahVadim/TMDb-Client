package com.example.feature_movieslist.di

import android.content.Context
import androidx.room.Room
import com.example.core.di.FeatureScope
import com.example.feature_movieslist.data.db.FavoriteMoviesDao
import com.example.feature_movieslist.data.db.FavoriteMoviesDb
import com.example.feature_movieslist.data.db.migrations.Migration1to2
import com.example.feature_movieslist.data.db.migrations.Migration2to3
import com.example.feature_movieslist.data.network.FavoritesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class FavoritesListModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FeatureScope
        fun provideFavoritesApi(retrofit: Retrofit): FavoritesApi {
            return retrofit.create(FavoritesApi::class.java)
        }

        @Provides
        @JvmStatic
        @FeatureScope
        fun provideFavoriteMoviesDao(context: Context): FavoriteMoviesDao {
            return Room.databaseBuilder(
                context,
                FavoriteMoviesDb::class.java,
                FavoriteMoviesDb.DATABASE_NAME
            )
                .addMigrations(Migration1to2(), Migration2to3())
                .build()
                .favoriteMoviesDao()
        }
    }
}
