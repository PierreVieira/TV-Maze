package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.presentation.model.SearchState
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import tvmaze.feature.search.presentation.generated.resources.Res
import tvmaze.feature.search.presentation.generated.resources.search

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    state: SearchState,
    onEvent: (SearchUiEvent) -> Unit,
) {
    SearchBar(
        modifier = if (state.searchBarPosition == SearchBarPosition.BOTTOM) {
            modifier.padding(bottom = 16.dp)
        } else {
            modifier
        },
        expanded = false,
        onExpandedChange = {},
        inputField = {
            SearchBarDefaults.InputField(
                query = state.query,
                onQueryChange = { onEvent(SearchUiEvent.OnQueryChange(it)) },
                onSearch = { onEvent(SearchUiEvent.OnSearch) },
                expanded = false,
                onExpandedChange = {},
                placeholder = { Text(stringResource(Res.string.search)) },
                leadingIcon = {
                    SearchBarIcon(
                        model = state.iconsModel.leadingIcon,
                        onEvent = onEvent
                    )
                },
                trailingIcon = {
                    Box {
                        SearchBarIcon(
                            model = state.iconsModel.trailingIcon,
                            onEvent = onEvent
                        )
                        SearchOptionsMenu(state, onEvent)
                    }
                },
            )
        },
    ) {

    }
}
