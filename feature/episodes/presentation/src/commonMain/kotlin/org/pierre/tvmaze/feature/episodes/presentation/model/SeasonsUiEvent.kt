package org.pierre.tvmaze.feature.episodes.presentation.model

import org.pierre.tvmaze.model.common.episode.EpisodeModel

sealed interface SeasonsUiEvent {
    data class ToggleSeason(val seasonNumber: Int): SeasonsUiEvent
    data class ToggleEpisodeWatched(val episode: EpisodeModel): SeasonsUiEvent
}
