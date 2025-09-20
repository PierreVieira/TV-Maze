package org.pierre.tvmaze.core.room_provider.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "last_searches",
    indices = [Index(value = ["query"], unique = true)]
)
data class LastSearchEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val query: String,
    val timestamp: Long,
)
