package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
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
    val onExpandedChange: (Boolean) -> Unit = { onEvent(SearchUiEvent.OnExpandedChange(it)) }
    val isExpanded = state.isExpanded

    SearchBar(
        modifier = modifier,
        expanded = isExpanded,
        onExpandedChange = onExpandedChange,
        inputField = {
            SearchBarDefaults.InputField(
                query = state.query,
                onQueryChange = { onEvent(SearchUiEvent.OnQueryChange(it)) },
                onSearch = { onEvent(SearchUiEvent.OnSearch(it)) },
                expanded = isExpanded,
                onExpandedChange = onExpandedChange,
                placeholder = { Text(stringResource(Res.string.search)) },
                leadingIcon = {
                    SearchBarIcon(
                        model = state.iconsModel.leadingIcon,
                        onEvent = onEvent
                    )
                },
                trailingIcon = {
                    state.iconsModel.trailingIcon?.let {
                        SearchBarIcon(
                            model = it,
                            onEvent = onEvent
                        )
                    }
                },
            )
        },
    ) {
    }
}
