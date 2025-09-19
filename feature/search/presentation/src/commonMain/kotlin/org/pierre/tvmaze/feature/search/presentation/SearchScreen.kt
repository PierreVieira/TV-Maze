package org.pierre.tvmaze.feature.search.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.pierre.tvmaze.feature.search.presentation.component.SearchBarComponent
import org.pierre.tvmaze.feature.search.presentation.model.SearchState
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchState,
    onEvent: (SearchUiEvent) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        SearchBarComponent(
            modifier = Modifier.align(Alignment.TopCenter),
            state = state,
            onEvent = onEvent
        )
    }
}
