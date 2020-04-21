package com.example.feature_movieslist.data.db.migrations

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.feature_movieslist.data.db.FavoriteMoviesDb
import com.example.feature_movieslist.data.db.MovieDb
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class Migration1to2Test {

    companion object {
        private const val TEST_DB = "migration-test"

        private val MOVIE_ID = "id" to 214
        private val MOVIE_POSTER_URL = "poster_url" to "movie_poster"
        private val MOVIE_TITLE = "title" to "movie_title"
        private val MOVIE_ORIGIN_TITLE = "origin_title" to "movie_origin_title"
        private val MOVIE_DESCRIPTION = "description" to "movie_description"
        private val MOVIE_GENRE = "genre" to "movie_genre"
        private val MOVIE_RATING = "rating" to 4.4
        private val MOVIE_RATING_COUNT = "rating_count" to 5432
        private val MOVIE_DURATION = "duration" to "movie_duration"
        private val MOVIE_IS_WATCHED_DEFAULT = MovieDb.NEW_FIELD_IS_WATCHED_NAME to false

        private val expectedMovieDb = MovieDb(
            id = MOVIE_ID.second,
            posterUrl = MOVIE_POSTER_URL.second,
            title = MOVIE_TITLE.second,
            originTitle = MOVIE_ORIGIN_TITLE.second,
            description = MOVIE_DESCRIPTION.second,
            genre = MOVIE_GENRE.second,
            rating = MOVIE_RATING.second,
            ratingCount = MOVIE_RATING_COUNT.second,
            duration = MOVIE_DURATION.second,
            isWatched = MOVIE_IS_WATCHED_DEFAULT.second
        )
    }

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        FavoriteMoviesDb::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun testMigrationFromV1() {
        val db = helper.createDatabase(TEST_DB, Migrations.VERSION_1)
        db.insert(MovieDb.TABLE_NAME, SQLiteDatabase.CONFLICT_REPLACE, getV1MovieContentValues())
        db.close()

        val migratedDatabase = getMigratedRoomDatabase()
        val movieFromDb = migratedDatabase.favoriteMoviesDao().getAll().blockingFirst().first()
        Assert.assertEquals(expectedMovieDb, movieFromDb)
    }

    private fun getMigratedRoomDatabase(): FavoriteMoviesDb {
        val database: FavoriteMoviesDb = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavoriteMoviesDb::class.java, TEST_DB
        )
            .addMigrations(Migration1to2())
            .build()
        helper.closeWhenFinished(database)
        return database
    }

    private fun getV1MovieContentValues(): ContentValues {
        return ContentValues().apply {
            put(MOVIE_ID.first, MOVIE_ID.second)
            put(MOVIE_POSTER_URL.first, MOVIE_POSTER_URL.second)
            put(MOVIE_TITLE.first, MOVIE_TITLE.second)
            put(MOVIE_ORIGIN_TITLE.first, MOVIE_ORIGIN_TITLE.second)
            put(MOVIE_DESCRIPTION.first, MOVIE_DESCRIPTION.second)
            put(MOVIE_GENRE.first, MOVIE_GENRE.second)
            put(MOVIE_RATING.first, MOVIE_RATING.second)
            put(MOVIE_RATING_COUNT.first, MOVIE_RATING_COUNT.second)
            put(MOVIE_DURATION.first, MOVIE_DURATION.second)
        }
    }
}
