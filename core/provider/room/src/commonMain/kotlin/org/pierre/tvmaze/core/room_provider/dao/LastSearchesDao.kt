package org.pierre.tvmaze.core.room_provider.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.core.room_provider.entity.LastSearchEntity

@Dao
interface LastSearchesDao {
    @Query("SELECT * FROM last_searches ORDER BY timestamp DESC")
    fun observeAll(): Flow<List<LastSearchEntity>>

    @Query("SELECT * FROM last_searches ORDER BY timestamp DESC")
    suspend fun getAll(): List<LastSearchEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(entity: LastSearchEntity): Long

    @Update
    suspend fun update(entity: LastSearchEntity)

    @Query("DELETE FROM last_searches WHERE id IN (:ids)")
    suspend fun deleteByIds(ids: List<Long>)

    @Query("DELETE FROM last_searches")
    suspend fun clear()

    @Transaction
    suspend fun upsertByQuery(query: String, timestamp: Long, maxItems: Int) {
        // Try insert; if exists update timestamp
        val insertedId = insert(LastSearchEntity(query = query, timestamp = timestamp))
        if (insertedId == -1L) {
            val current = getAll().firstOrNull { it.query == query }
            if (current != null) {
                update(current.copy(timestamp = timestamp))
            }
        }
        // Enforce max size
        val all = getAll()
        if (all.size > maxItems) {
            val toDelete = all.sortedByDescending { it.timestamp }
                .drop(maxItems)
                .map { it.id }
            if (toDelete.isNotEmpty()) deleteByIds(toDelete)
        }
    }
}
