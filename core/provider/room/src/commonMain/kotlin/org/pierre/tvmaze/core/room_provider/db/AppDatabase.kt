package org.pierre.tvmaze.core.room_provider.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import org.pierre.tvmaze.core.room_provider.dao.LastSearchesDao
import org.pierre.tvmaze.core.room_provider.entity.LastSearchEntity

@Database(
    entities = [LastSearchEntity::class],
    version = 1,
    exportSchema = false,
)
@ConstructedBy(DatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lastSearchDao(): LastSearchesDao
}
