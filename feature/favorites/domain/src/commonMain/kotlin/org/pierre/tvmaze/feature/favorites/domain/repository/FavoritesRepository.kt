package org.pierre.tvmaze.feature.favorites.domain.repository

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.model.common.ShowItemModel

interface FavoritesRepository {
    suspend fun toggleFavorite(show: ShowItemModel): Result<Unit>
    fun getAllFavoritesAsFlow(): Flow<List<ShowItemModel>>
    suspend fun getAllFavorites(): List<ShowItemModel>
}
