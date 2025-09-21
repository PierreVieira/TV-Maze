package org.pierre.tvmaze.feature.favorites.domain.repository

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.model.common.MediaItemModel

interface FavoritesRepository {
    suspend fun toggleFavorite(show: MediaItemModel): Result<Unit>
    fun getAllFavoritesAsFlow(): Flow<List<MediaItemModel>>
    suspend fun getAllFavorites(): List<MediaItemModel>
}
