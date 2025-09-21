package org.pierre.tvmaze.ui.navigation.presentation.graph

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.tvmaze.feature.episodes.presentation.component.episodesSeasonsList
import org.pierre.tvmaze.feature.media_details.presentation.MediaDetailsScreen
import org.pierre.tvmaze.feature.media_details.presentation.MediaDetailsViewModel
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsRoute
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiAction
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiEvent
import org.pierre.tvmaze.model.data_status.toLoadedInformation
import org.pierre.tvmaze.ui.utils.ActionCollector

internal fun NavGraphBuilder.detailMedia(navHostController: NavHostController) {
    composable<MediaDetailsRoute> {
        val viewModel: MediaDetailsViewModel = koinViewModel()
        val state by viewModel.uiState.collectAsState()
        val seasons by viewModel.seasons.collectAsState()
        val isSummaryExpanded by viewModel.isSummaryExpanded.collectAsState()
        val initiallyCollapsed: Set<Int> = remember(seasons) {
            seasons.mapNotNull { it.number.toLoadedInformation()?.data }.toSet()
        }
        var collapsedSeasons: Set<Int> by remember(seasons) { mutableStateOf(initiallyCollapsed) }
        ActionCollector(viewModel.uiAction) { action ->
            when (action) {
                MediaDetailsUiAction.NavigateBack -> navHostController.navigateUp()
            }
        }
        MediaDetailsScreen(
            mediaItemModel = state,
            isSummaryExpanded = isSummaryExpanded,
            onEvent = viewModel::onEvent,
            itemsOnBottom = {
                episodesSeasonsList(
                    seasons = seasons,
                    onEpisodeCheckedChange = { viewModel.onEvent(MediaDetailsUiEvent.OnToggleEpisodeWatched(it)) },
                    onToggleSeason = { seasonNumber ->
                        collapsedSeasons = if (collapsedSeasons.contains(seasonNumber)) {
                            collapsedSeasons - seasonNumber
                        } else {
                            collapsedSeasons + seasonNumber
                        }
                    },
                    collapsedSeasons = collapsedSeasons
                )
            },
        )
    }
}
