package org.pierre.tvmaze.ui.navigation.presentation.graph

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.tvmaze.feature.episodes.presentation.seasonsList
import org.pierre.tvmaze.feature.episodes.presentation.viewmodel.SeasonsViewModel
import org.pierre.tvmaze.feature.media_details.presentation.MediaDetailsScreen
import org.pierre.tvmaze.feature.media_details.presentation.MediaDetailsViewModel
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiAction
import org.pierre.tvmaze.model.common.route.MediaDetailsRoute
import org.pierre.tvmaze.ui.utils.ActionCollector

internal fun NavGraphBuilder.detailMedia(navHostController: NavHostController) {
    composable<MediaDetailsRoute> {
        val mediaDetailsViewModel: MediaDetailsViewModel = koinViewModel()
        val seasonsViewModel: SeasonsViewModel = koinViewModel()
        val medias by mediaDetailsViewModel.uiState.collectAsState()
        val seasons by seasonsViewModel.seasons.collectAsState()
        ActionCollector(mediaDetailsViewModel.uiAction) { action ->
            when (action) {
                MediaDetailsUiAction.NavigateBack -> navHostController.navigateUp()
            }
        }
        MediaDetailsScreen(
            state = medias,
            onEvent = mediaDetailsViewModel::onEvent,
            itemsOnBottom = {
                seasonsList(
                    seasons = seasons,
                    onEvent = seasonsViewModel::onEvent,
                )
            },
        )
    }
}
