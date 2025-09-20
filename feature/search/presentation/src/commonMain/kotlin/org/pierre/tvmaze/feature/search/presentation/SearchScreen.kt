package org.pierre.tvmaze.feature.search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import org.pierre.ui.components.show_item_card.ShowItemCardComponent


private val maxScreenWidth = 700.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    state: SearchState,
    onEvent: (SearchUiEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        SearchScreenContent(
            state = state,
            paddingValues = paddingValues,
            onEvent = onEvent,
        )
    }
}

@Composable
private fun SearchScreenContent(
    state: SearchState,
    paddingValues: PaddingValues,
    onEvent: (SearchUiEvent) -> Unit,
) {
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

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .widthIn(max = maxScreenWidth)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(state.searchItems) { searchItem ->
                ShowItemCardComponent(
                    modifier = Modifier.fillMaxWidth(),
                    showItemModel = searchItem,
                )
            }
        }

        if (!isSearchBarOnTop) {
            VerticalSpacer()
            SearchBarComponent(
                state = state,
                onEvent = onEvent,
            )
        }
    }
}
