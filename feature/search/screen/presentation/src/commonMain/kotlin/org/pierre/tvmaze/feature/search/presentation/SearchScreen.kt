package org.pierre.tvmaze.feature.search.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.presentation.component.SearchBarComponent
import org.pierre.tvmaze.feature.search.presentation.component.SearchScreenContent
import org.pierre.tvmaze.feature.search.presentation.component.SearchScreenLayout
import org.pierre.tvmaze.feature.search.presentation.model.SearchState
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer

@Composable
fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    state: SearchState,
    onEvent: (SearchUiEvent) -> Unit,
) {
    SearchScreenLayout(
        snackbarHostState = snackbarHostState,
        searchBarPosition = state.searchBar.position,
        searchBarComponent = {
            SearchBarComponent(
                state = state.searchBar,
                onEvent = onEvent
            )
        },
        content = {
            SearchScreenContent(
                model = state.content,
                onEvent = onEvent,
                lastItem = {
                    if (state.searchBar.position == SearchBarPosition.BOTTOM) {
                        VerticalSpacer()
                    }
                }
            )
        },
    )
}
