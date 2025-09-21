package org.pierre.tvmaze.feature.favorites.domain.repository

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.model.common.MediaItemCard

interface FavoritesRepository {
    suspend fun toggleFavorite(show: MediaItemCard): Result<Unit>
    fun getAllFavoritesAsFlow(): Flow<List<MediaItemCard>>
    suspend fun getAllFavorites(): List<MediaItemCard>
}
