package org.pierre.tvmaze.core.room_provider.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.core.room_provider.entity.SearchHistoryItemEntity

@Dao
interface LastSearchesDao {
    @Query("SELECT * FROM last_searches ORDER BY timestamp DESC")
    fun getAllAsFlow(): Flow<List<SearchHistoryItemEntity>>

    @Query("SELECT * FROM last_searches ORDER BY timestamp DESC")
    suspend fun getAll(): List<SearchHistoryItemEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(entity: SearchHistoryItemEntity): Long

    @Update
    suspend fun update(entity: SearchHistoryItemEntity)

    @Query("DELETE FROM last_searches WHERE id IN (:ids)")
    suspend fun deleteByIds(ids: List<Long>)

    @Query("DELETE FROM last_searches")
    suspend fun clear()
}
