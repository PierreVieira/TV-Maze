package org.pierre.tvmaze.feature.favorites.domain.usecase.impl

import org.pierre.tvmaze.feature.favorites.domain.repository.FavoritesRepository
import org.pierre.tvmaze.feature.favorites.domain.usecase.ToggleFavorite
import org.pierre.tvmaze.model.common.media.MediaItemModel

internal class ToggleFavoriteUseCase(
    private val repository: FavoritesRepository,
) : ToggleFavorite {
    override suspend fun invoke(show: MediaItemModel): Result<Unit> = repository.toggleFavorite(show)
}
