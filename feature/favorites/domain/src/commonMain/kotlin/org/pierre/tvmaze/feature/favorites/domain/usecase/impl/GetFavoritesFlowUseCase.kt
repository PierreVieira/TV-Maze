package org.pierre.tvmaze.feature.favorites.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.feature.favorites.domain.repository.FavoritesRepository
import org.pierre.tvmaze.feature.favorites.domain.usecase.GetFavoritesFlow
import org.pierre.tvmaze.model.common.MediaItemCard

internal class GetFavoritesFlowUseCase(
    private val repository: FavoritesRepository,
) : GetFavoritesFlow {
    override fun invoke(): Flow<List<MediaItemCard>> = repository.getAllFavoritesAsFlow()
}
