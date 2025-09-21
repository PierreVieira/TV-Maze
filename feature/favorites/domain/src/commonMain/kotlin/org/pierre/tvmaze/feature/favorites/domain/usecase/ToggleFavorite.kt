package org.pierre.tvmaze.feature.favorites.domain.usecase

import org.pierre.tvmaze.model.common.media.MediaItemModel

fun interface ToggleFavorite {
    suspend operator fun invoke(show: MediaItemModel): Result<Unit>
}
