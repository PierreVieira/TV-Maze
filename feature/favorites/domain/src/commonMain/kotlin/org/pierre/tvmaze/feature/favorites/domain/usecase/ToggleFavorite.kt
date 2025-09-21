package org.pierre.tvmaze.feature.favorites.domain.usecase

import org.pierre.tvmaze.model.common.ShowItemModel

fun interface ToggleFavorite {
    suspend operator fun invoke(show: ShowItemModel): Result<Unit>
}
