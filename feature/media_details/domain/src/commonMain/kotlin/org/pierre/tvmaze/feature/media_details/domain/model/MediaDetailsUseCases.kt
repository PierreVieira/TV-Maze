package org.pierre.tvmaze.feature.media_details.domain.model

import org.pierre.tvmaze.feature.favorites.domain.usecase.GetFavoritesFlow
import org.pierre.tvmaze.feature.favorites.domain.usecase.ToggleFavorite
import org.pierre.tvmaze.feature.media_details.domain.usecase.GetMediaDetails

data class MediaDetailsUseCases(
    val getMediaDetails: GetMediaDetails,
    val toggleFavorite: ToggleFavorite,
    val getFavoritesFlow: GetFavoritesFlow,
)
