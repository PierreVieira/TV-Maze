package org.pierre.tvmaze.feature.media_details.presentation.model

sealed interface MediaDetailsUiEvent {
    data object OnBackClick : MediaDetailsUiEvent
    data class OnFavoriteClick(val id: Long) : MediaDetailsUiEvent
    data object OnToggleSummaryExpansion : MediaDetailsUiEvent
}
