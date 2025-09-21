package org.pierre.tvmaze.core.room_provider.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import org.pierre.tvmaze.core.room_provider.dao.FavoriteShowsDao
import org.pierre.tvmaze.core.room_provider.dao.LastSearchesDao
import org.pierre.tvmaze.core.room_provider.entity.FavoriteShowEntity
import org.pierre.tvmaze.core.room_provider.entity.SearchHistoryItemEntity

@Database(
    entities = [SearchHistoryItemEntity::class, FavoriteShowEntity::class],
    version = 2,
    exportSchema = false,
)
@ConstructedBy(DatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lastSearchDao(): LastSearchesDao
    abstract fun favoriteShowsDao(): FavoriteShowsDao
}
