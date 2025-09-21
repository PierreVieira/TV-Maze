package org.pierre.tvmaze.ui.navigation.presentation.graph

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.tvmaze.feature.media_details.presentation.MediaDetailsScreen
import org.pierre.tvmaze.feature.media_details.presentation.MediaDetailsViewModel
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsRoute
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiAction
import org.pierre.tvmaze.ui.utils.ActionCollector

internal fun NavGraphBuilder.detailMedia(navHostController: NavHostController) {
    composable<MediaDetailsRoute> {
        val viewModel: MediaDetailsViewModel = koinViewModel()
        val state by viewModel.uiState.collectAsState()
        val isSummaryExpanded by viewModel.isSummaryExpanded.collectAsState()
        ActionCollector(viewModel.uiAction) { action ->
            when (action) {
                MediaDetailsUiAction.NavigateBack -> navHostController.navigateUp()
            }
        }
        MediaDetailsScreen(
            mediaItemModel = state,
            isSummaryExpanded = isSummaryExpanded,
            onEvent = viewModel::onEvent,
        )
    }
}
