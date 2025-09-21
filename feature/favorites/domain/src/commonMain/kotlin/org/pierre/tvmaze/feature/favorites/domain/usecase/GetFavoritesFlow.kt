package org.pierre.tvmaze.feature.favorites.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.model.common.MediaItemCard

fun interface GetFavoritesFlow {
    operator fun invoke(): Flow<List<MediaItemCard>>
}
