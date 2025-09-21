package org.pierre.tvmaze.feature.favorites.domain.repository

import org.pierre.tvmaze.model.common.ShowItemModel

fun interface FavoritesRepository {
    suspend fun toggleFavorite(show: ShowItemModel): Result<Unit>
}
