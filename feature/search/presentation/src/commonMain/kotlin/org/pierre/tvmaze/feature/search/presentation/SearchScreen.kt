package org.pierre.tvmaze.feature.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.presentation.component.SearchBarComponent
import org.pierre.tvmaze.feature.search.presentation.model.SearchState
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    state: SearchState,
    onEvent: (SearchUiEvent) -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        val isSearchBarOnTop = state.searchBarPosition == SearchBarPosition.TOP
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    if (isSearchBarOnTop) {
                        PaddingValues(0.dp)
                    } else {
                        paddingValues
                    }
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (isSearchBarOnTop) {
                SearchBarComponent(
                    state = state,
                    onEvent = onEvent,
                )
                VerticalSpacer()
            }
            content()
            if (!isSearchBarOnTop) {
                VerticalSpacer()
                SearchBarComponent(
                    state = state,
                    onEvent = onEvent,
                )
            }
        }
    }
}
