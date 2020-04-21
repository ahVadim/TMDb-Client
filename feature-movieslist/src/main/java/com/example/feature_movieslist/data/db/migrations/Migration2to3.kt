package com.example.feature_movieslist.data.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.feature_movieslist.data.db.MovieDb

class Migration2to3 : Migration(Migrations.VERSION_2, Migrations.VERSION_3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            """
                    CREATE TABLE IF NOT EXISTS new_movie (
                            id INTEGER NOT NULL,
                            poster_url TEXT,
                            title TEXT NOT NULL,
                            origin_title TEXT NOT NULL,
                            description TEXT NOT NULL,
                            genre TEXT NOT NULL,
                            rating REAL NOT NULL,
                            rating_count INTEGER NOT NULL,
                            duration TEXT,
                            is_worth_watching INTEGER NOT NULL,
                            PRIMARY KEY(id))
                    """
        )

        database.execSQL(
            """
            INSERT INTO new_movie (id, poster_url, title, origin_title, description, genre, rating, rating_count, duration, is_worth_watching)
            SELECT id, poster_url, title, origin_title, description, genre, rating, rating_count, duration, is_watched FROM ${MovieDb.TABLE_NAME}
        """
        )

        database.execSQL("DROP TABLE ${MovieDb.TABLE_NAME}")
        database.execSQL("ALTER TABLE new_movie RENAME TO ${MovieDb.TABLE_NAME}")
    }
}
