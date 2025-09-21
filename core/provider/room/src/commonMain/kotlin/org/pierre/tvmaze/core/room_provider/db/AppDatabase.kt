package org.pierre.tvmaze.core.room_provider.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import org.pierre.tvmaze.core.room_provider.dao.FavoriteMediasDao
import org.pierre.tvmaze.core.room_provider.dao.LastSearchesDao
import org.pierre.tvmaze.core.room_provider.dao.WatchedEpisodesDao
import org.pierre.tvmaze.core.room_provider.entity.FavoriteMediaEntity
import org.pierre.tvmaze.core.room_provider.entity.SearchHistoryItemEntity
import org.pierre.tvmaze.core.room_provider.entity.WatchedEpisodeEntity

@Database(
    entities = [SearchHistoryItemEntity::class, FavoriteMediaEntity::class, WatchedEpisodeEntity::class],
    version = 6,
    exportSchema = false,
)
@ConstructedBy(DatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lastSearchDao(): LastSearchesDao
    abstract fun favoriteMediasDao(): FavoriteMediasDao
    abstract fun watchedEpisodesDao(): WatchedEpisodesDao
}
