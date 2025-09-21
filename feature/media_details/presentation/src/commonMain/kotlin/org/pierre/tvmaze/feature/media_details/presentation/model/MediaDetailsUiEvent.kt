package org.pierre.tvmaze.feature.media_details.presentation.model

import org.pierre.tvmaze.model.common.episode.EpisodeModel

sealed interface MediaDetailsUiEvent {
    data object OnBackClick : MediaDetailsUiEvent
    data class OnFavoriteClick(val id: Long) : MediaDetailsUiEvent
    data object OnToggleSummaryExpansion : MediaDetailsUiEvent
    data class OnToggleEpisodeWatched(val episode: EpisodeModel) : MediaDetailsUiEvent
}
