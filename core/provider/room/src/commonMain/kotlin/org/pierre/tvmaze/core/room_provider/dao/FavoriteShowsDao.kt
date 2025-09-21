package org.pierre.tvmaze.core.room_provider.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.core.room_provider.entity.FavoriteShowEntity

@Dao
interface FavoriteShowsDao {
    @Query("SELECT * FROM favorite_shows ORDER BY name ASC")
    fun getAllAsFlow(): Flow<List<FavoriteShowEntity>>

    @Query("SELECT * FROM favorite_shows ORDER BY name ASC")
    suspend fun getAll(): List<FavoriteShowEntity>

    @Query("SELECT * FROM favorite_shows WHERE id = :id")
    suspend fun getById(id: Long): FavoriteShowEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: FavoriteShowEntity)

    @Update
    suspend fun update(entity: FavoriteShowEntity)

    @Query("DELETE FROM favorite_shows WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM favorite_shows")
    suspend fun clear()
}
