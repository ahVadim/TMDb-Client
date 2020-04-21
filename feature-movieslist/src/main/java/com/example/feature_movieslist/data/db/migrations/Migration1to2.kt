package com.example.feature_movieslist.data.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.feature_movieslist.data.db.MovieDb

class Migration1to2 : Migration(Migrations.VERSION_1, Migrations.VERSION_2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            """
                    ALTER TABLE ${MovieDb.TABLE_NAME} 
                    ADD COLUMN is_watched INTEGER DEFAULT 0 NOT NULL
                    """
        )
    }
}
