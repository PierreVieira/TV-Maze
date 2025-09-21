package org.pierre.tvmaze.feature.favorites.presentation.model

import org.pierre.tvmaze.model.common.ShowItemModel

data class FavoritesState(
    val items: List<ShowItemModel> = emptyList()
)
