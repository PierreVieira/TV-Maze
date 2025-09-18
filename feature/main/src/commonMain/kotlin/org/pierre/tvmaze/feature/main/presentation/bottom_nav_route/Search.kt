package org.pierre.tvmaze.feature.main.presentation.bottom_nav_route

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavRoute
import org.pierre.tvmaze.feature.search.presentation.SearchScreen
import org.pierre.tvmaze.feature.search.presentation.viewmodel.SearchViewModel

fun NavGraphBuilder.search() {
    composable<BottomNavRoute.Search> {
        val viewModel = koinViewModel<SearchViewModel>()
        val state by viewModel.state.collectAsState()
        SearchScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}
