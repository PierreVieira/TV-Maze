package org.pierre.tvmaze.feature.main.presentation.bottom_nav_route

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.tvmaze.feature.favorites.presentation.FavoritesScreen
import org.pierre.tvmaze.feature.favorites.presentation.viewmodel.FavoritesViewModel
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavRoute

fun NavGraphBuilder.favorites() {
    composable<BottomNavRoute.Favorites> {
        val viewModel: FavoritesViewModel = koinViewModel()
        val state by viewModel.uiState.collectAsState()
        FavoritesScreen(
            items = state,
            onEvent = viewModel::onEvent,
        )
    }
}
