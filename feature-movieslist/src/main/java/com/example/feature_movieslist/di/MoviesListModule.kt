package com.example.feature_movieslist.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.core.di.FeatureScope
import com.example.core.di.viewmodel.ViewModelKey
import com.example.core.di.viewmodel.ViewModelModule
import com.example.feature_movieslist.data.db.FavoriteMoviesDao
import com.example.feature_movieslist.data.db.FavoriteMoviesDb
import com.example.feature_movieslist.data.network.SearchApi
import com.example.feature_movieslist.presentation.movies.MoviesListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [ViewModelModule::class])
abstract class MoviesListModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FeatureScope
        fun provideSessionApi(retrofit: Retrofit): SearchApi {
            return retrofit.create(SearchApi::class.java)
        }

        @Provides
        @JvmStatic
        @FeatureScope
        fun provideFavoriteMoviesDao(context: Context): FavoriteMoviesDao {
            return Room.databaseBuilder(
                context,
                FavoriteMoviesDb::class.java,
                "favorites-movies-db"
            )
                .build()
                .favoriteMoviesDao()
        }
    }

    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    abstract fun provideAuthViewModel(viewModel: MoviesListViewModel): ViewModel
}
