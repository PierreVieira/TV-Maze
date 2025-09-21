package org.pierre.tvmaze.feature.favorites.domain.usecase

import org.pierre.tvmaze.model.common.media.MediaItemModel

fun interface GetFavorites {
    suspend operator fun invoke(): List<MediaItemModel>
}
