package org.pierre.tvmaze.feature.favorites.presentation.model

sealed interface FavoritesUiAction {
    data class NavigateToMediaDetails(val id: Long) : FavoritesUiAction
}
