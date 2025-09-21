package org.pierre.tvmaze.core.room_provider.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.core.room_provider.entity.WatchedEpisodeEntity

@Dao
interface WatchedEpisodesDao {
    @Query("SELECT * FROM watched_episodes WHERE mediaId = :mediaId ORDER BY season ASC, number ASC")
    fun getByMediaIdAsFlow(mediaId: Long): Flow<List<WatchedEpisodeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: WatchedEpisodeEntity)

    @Query("DELETE FROM watched_episodes WHERE id = :id")
    suspend fun deleteById(id: Long)
}
