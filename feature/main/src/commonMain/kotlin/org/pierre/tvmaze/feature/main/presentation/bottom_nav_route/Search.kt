package org.pierre.tvmaze.feature.main.presentation.bottom_nav_route

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.feature.search.warning.delete_item.presentation.model.DeleteSearchItemDialogRoute
import org.pierre.feature.search.warning.delete_all.presentation.model.DeleteAllSearchDialogRoute
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavRoute
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsRoute
import org.pierre.tvmaze.feature.search.presentation.SearchScreen
import org.pierre.tvmaze.feature.search.presentation.utils.SearchActionCollector
import org.pierre.tvmaze.feature.search.presentation.viewmodel.SearchViewModel

fun NavGraphBuilder.search(appNavHostController: NavHostController) {
    composable<BottomNavRoute.Search> {
        val viewModel = koinViewModel<SearchViewModel>()
        val state by viewModel.uiState.collectAsState()
        val snackbarHostState = remember { SnackbarHostState() }
        SearchActionCollector(
            uiAction = viewModel.uiAction,
            snackbarHostState = snackbarHostState,
            goToDeleteSearchHistoryItem = { id: Long, name: String ->
                appNavHostController.navigate(DeleteSearchItemDialogRoute(id, name))
            },
            goToDeleteAllSearchHistory = {
                appNavHostController.navigate(DeleteAllSearchDialogRoute)
            },
            goToMediaDetails = { id: Long ->
                appNavHostController.navigate(MediaDetailsRoute(id))
            }
        )
        SearchScreen(
            snackbarHostState = snackbarHostState,
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}
