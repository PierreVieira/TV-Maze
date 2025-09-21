package org.pierre.tvmaze.core.room_provider.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "watched_episodes",
    indices = [Index(value = ["mediaId"])],
)
data class WatchedEpisodeEntity(
    @PrimaryKey val id: Long,
    val mediaId: Long,
    val name: String?,
    val season: Int?,
    val number: Int?,
    val originalImageUrl: String?,
    val mediumImageUrl: String?,
)
