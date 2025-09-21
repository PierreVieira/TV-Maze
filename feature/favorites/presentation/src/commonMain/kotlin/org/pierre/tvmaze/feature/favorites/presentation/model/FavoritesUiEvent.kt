package org.pierre.tvmaze.feature.favorites.presentation.model

sealed interface FavoritesUiEvent {
    data class OnFavoriteItemClick(val id: Long) : FavoritesUiEvent
    data class OnItemClick(val id: Long) : FavoritesUiEvent
}
