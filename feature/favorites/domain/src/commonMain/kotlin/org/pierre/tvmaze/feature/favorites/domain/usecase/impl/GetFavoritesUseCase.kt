package org.pierre.tvmaze.feature.favorites.domain.usecase.impl

import org.pierre.tvmaze.feature.favorites.domain.repository.FavoritesRepository
import org.pierre.tvmaze.feature.favorites.domain.usecase.GetFavorites
import org.pierre.tvmaze.model.common.ShowItemModel

internal class GetFavoritesUseCase(
    private val repository: FavoritesRepository,
) : GetFavorites {
    override suspend fun invoke(): List<ShowItemModel> = repository.getAllFavorites()
}
