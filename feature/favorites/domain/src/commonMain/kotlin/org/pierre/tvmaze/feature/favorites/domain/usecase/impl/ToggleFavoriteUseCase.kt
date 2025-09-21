package org.pierre.tvmaze.feature.favorites.domain.usecase.impl

import org.pierre.tvmaze.feature.favorites.domain.repository.FavoritesRepository
import org.pierre.tvmaze.feature.favorites.domain.usecase.ToggleFavorite
import org.pierre.tvmaze.model.common.ShowItemModel

class ToggleFavoriteUseCase(
    private val repository: FavoritesRepository,
) : ToggleFavorite {
    override suspend fun invoke(show: ShowItemModel): Result<Unit> {
        return repository.toggleFavorite(show)
    }
}
