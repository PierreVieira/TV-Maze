package org.pierre.tvmaze.feature.main.presentation.bottom_nav_route

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavRoute
import org.pierre.tvmaze.feature.search.presentation.SearchScreen
import org.pierre.tvmaze.feature.search.presentation.utils.SearchActionCollector
import org.pierre.tvmaze.feature.search.presentation.viewmodel.SearchViewModel

fun NavGraphBuilder.search() {
    composable<BottomNavRoute.Search> {
        val viewModel = koinViewModel<SearchViewModel>()
        val state by viewModel.uiState.collectAsState()
        val snackbarHostState = remember { SnackbarHostState() }
        SearchActionCollector(
            uiAction = viewModel.uiAction,
            snackbarHostState = snackbarHostState
        )
        SearchScreen(
            snackbarHostState = snackbarHostState,
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}
