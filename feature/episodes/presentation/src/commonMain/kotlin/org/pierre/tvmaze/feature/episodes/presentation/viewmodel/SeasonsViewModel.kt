package org.pierre.tvmaze.feature.episodes.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.feature.episodes.domain.usecase.GetEpisodesBySeason
import org.pierre.tvmaze.feature.episodes.domain.usecase.GetWatchedEpisodesBySeasonFlow
import org.pierre.tvmaze.feature.episodes.domain.usecase.ToggleEpisodeWatched
import org.pierre.tvmaze.feature.episodes.presentation.model.SeasonsUiEvent
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.common.route.MediaDetailsRoute
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.model.data_status.toLoadedStatus
import org.pierre.tvmaze.ui.utils.observe

class SeasonsViewModel(
    savedStateHandle: SavedStateHandle,
    getWatchedEpisodesBySeasonFlow: GetWatchedEpisodesBySeasonFlow,
    private val getEpisodesBySeason: GetEpisodesBySeason,
    private val toggleEpisodeWatched: ToggleEpisodeWatched,
): ViewModel() {
    private val _seasons: MutableStateFlow<List<SeasonModel>> = MutableStateFlow(emptyList())
    val seasons: StateFlow<List<SeasonModel>> = _seasons.asStateFlow()

    private val mediaId: Long = savedStateHandle.toRoute<MediaDetailsRoute>().id

    init {
        viewModelScope.launch {
            getEpisodesBySeason(mediaId).onSuccess { loadedSeasons ->
                _seasons.value = loadedSeasons
            }
        }
        observe(getWatchedEpisodesBySeasonFlow(mediaId)) { watchedSeasons ->
            applyWatchedOverlay(watchedSeasons)
        }
    }

    fun onEvent(seasonsUiEvent: SeasonsUiEvent) {
        when (seasonsUiEvent) {
            is SeasonsUiEvent.ToggleEpisodeWatched -> onToggleEpisodeWatched(seasonsUiEvent.episode)
            is SeasonsUiEvent.ToggleSeason -> onToggleSeason(seasonsUiEvent.seasonNumber)
        }
    }

    private fun applyWatchedOverlay(watchedSeasons: List<SeasonModel>) {
        val watchedIds: Set<Long> = watchedSeasons.flatMap { it.episodes }
            .mapNotNull { it.id.toLoadedData() }
            .toSet()
        val updated = _seasons.value.map { season ->
            season.copy(
                episodes = season.episodes.map { episode ->
                    val isWatched = watchedIds.contains(episode.id.toLoadedData())
                    episode.copy(isWatched = isWatched.toLoadedStatus())
                }
            )
        }
        _seasons.value = updated
    }

    private fun onToggleEpisodeWatched(episode: EpisodeModel) {
        viewModelScope.launch {
            toggleEpisodeWatched(episode)
        }
    }

    private fun onToggleSeason(seasonNumber: Int) {
        _seasons.update {
            it.map { season ->
                if (season.number.toLoadedData() == seasonNumber) {
                    season.copy(isCollapsed = !season.isCollapsed)
                } else {
                    season
                }
            }
        }
    }
}
