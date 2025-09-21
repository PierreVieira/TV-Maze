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
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonsUseCases
import org.pierre.tvmaze.feature.episodes.presentation.factory.LoadingSeasonsFactory
import org.pierre.tvmaze.feature.episodes.presentation.model.SeasonsUiEvent
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.common.route.MediaDetailsRoute
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.ui.utils.observe

class SeasonsViewModel(
    savedStateHandle: SavedStateHandle,
    loadingSeasonsFactory: LoadingSeasonsFactory,
    private val useCases: SeasonsUseCases,
) : ViewModel() {


    private val _seasons = MutableStateFlow(loadingSeasonsFactory.create())
    val seasons: StateFlow<List<SeasonModel>> = _seasons.asStateFlow()

    private var lastWatchedOverlay: List<SeasonModel> = emptyList()

    private val mediaId: Long = runCatching {
        savedStateHandle.toRoute<MediaDetailsRoute>().id
    }.getOrElse {
        savedStateHandle.get<Long>("id") ?: 0L
    }

    init {
        viewModelScope.launch {
            useCases.getEpisodesBySeason(mediaId).onSuccess { loadedSeasons ->
                _seasons.value = loadedSeasons
                // Re-apply any watched overlay that might have arrived earlier
                if (lastWatchedOverlay.isNotEmpty()) {
                    applyWatchedOverlay(lastWatchedOverlay)
                }
            }
        }
        observe(useCases.getWatchedEpisodesBySeasonFlow(mediaId)) { watchedSeasons ->
            lastWatchedOverlay = watchedSeasons
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
        val currentSeasonsValue = _seasons.value
        val updated = useCases.updateSeasonsWithWatched(watchedSeasons, currentSeasonsValue)
        _seasons.value = updated
    }

    private fun onToggleEpisodeWatched(episode: EpisodeModel) {
        viewModelScope.launch {
            useCases.toggleEpisodeWatched(episode)
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
