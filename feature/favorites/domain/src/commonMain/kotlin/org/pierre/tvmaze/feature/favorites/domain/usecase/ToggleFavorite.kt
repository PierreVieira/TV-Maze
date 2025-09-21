package org.pierre.tvmaze.feature.favorites.domain.usecase

import org.pierre.tvmaze.model.common.MediaItemCard

fun interface ToggleFavorite {
    suspend operator fun invoke(show: MediaItemCard): Result<Unit>
}
