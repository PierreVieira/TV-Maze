package org.pierre.tvmaze.feature.main.presentation.bottom_nav_route

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.tvmaze.feature.favorites.presentation.FavoritesScreen
import org.pierre.tvmaze.feature.favorites.presentation.model.FavoritesUiAction
import org.pierre.tvmaze.feature.favorites.presentation.viewmodel.FavoritesViewModel
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavRoute
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsRoute
import org.pierre.tvmaze.ui.utils.ActionCollector

fun NavGraphBuilder.favorites(appNavHostController: NavHostController) {
    composable<BottomNavRoute.Favorites> {
        val viewModel: FavoritesViewModel = koinViewModel()
        val state by viewModel.uiState.collectAsState()
        ActionCollector(viewModel.uiAction) { action: FavoritesUiAction ->
            when (action) {
                is FavoritesUiAction.NavigateToMediaDetails -> appNavHostController.navigate(MediaDetailsRoute(action.id))
            }
        }
        FavoritesScreen(
            items = state,
            onEvent = viewModel::onEvent,
        )
    }
}
