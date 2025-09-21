package org.pierre.tvmaze.core.room_provider.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite_shows",
    indices = [Index(value = ["name"], unique = false)]
)
data class FavoriteMediaEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val originalImageUrl: String?,
    val mediumImageUrl: String?,
    val startYear: Int?,
    val endYear: Int?,
    val averageRating: Double?,
    val fullStarsAmount: Int?,
    val showStarInAHalf: Boolean?,
)
