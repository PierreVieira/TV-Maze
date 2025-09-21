package org.pierre.tvmaze.core.room_provider.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.core.room_provider.entity.FavoriteMediaEntity

@Dao
interface FavoriteMediasDao {
    @Query("SELECT * FROM favorite_shows ORDER BY name ASC")
    fun getAllAsFlow(): Flow<List<FavoriteMediaEntity>>

    @Query("SELECT * FROM favorite_shows ORDER BY name ASC")
    suspend fun getAll(): List<FavoriteMediaEntity>

    @Query("SELECT * FROM favorite_shows WHERE id = :id")
    suspend fun getById(id: Long): FavoriteMediaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: FavoriteMediaEntity)

    @Update
    suspend fun update(entity: FavoriteMediaEntity)

    @Query("DELETE FROM favorite_shows WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM favorite_shows")
    suspend fun clear()
}
