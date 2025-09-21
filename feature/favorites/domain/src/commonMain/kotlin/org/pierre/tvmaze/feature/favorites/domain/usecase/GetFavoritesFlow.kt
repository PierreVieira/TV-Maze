package org.pierre.tvmaze.feature.favorites.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.model.common.ShowItemModel

fun interface GetFavoritesFlow {
    operator fun invoke(): Flow<List<ShowItemModel>>
}
