package org.pierre.tvmaze.feature.search.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        val onExpandedChange: (Boolean) -> Unit = { onEvent(SearchUiEvent.OnExpandedChange(it)) }
        val isExpanded = state.isExpanded

        SearchBar(
            modifier = Modifier.align(Alignment.TopCenter),
            expanded = isExpanded,
            onExpandedChange = onExpandedChange,
            shape = SearchBarDefaults.inputFieldShape,
            inputField = {
                SearchBarDefaults.InputField(
                    query = state.query,
                    onQueryChange = { onEvent(SearchUiEvent.OnQueryChange(it)) },
                    onSearch = { onEvent(SearchUiEvent.OnSearch(it)) },
                    expanded = isExpanded,
                    onExpandedChange = onExpandedChange,
                    placeholder = { Text("Search") },
                    leadingIcon = {
                        if (isExpanded) {
                            IconButton(onClick = { onEvent(SearchUiEvent.OnExpandedChange(false)) }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        } else {
                            IconButton(onClick = { onEvent(SearchUiEvent.OnExpandedChange(true)) }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search"
                                )
                            }
                        }
                    },
                    trailingIcon = {
                        if (isExpanded) {
                            IconButton(onClick = { onEvent(SearchUiEvent.OnQueryChange("")) }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Clear"
                                )
                            }
                        }
                    },
                )
            },
        ) {
            Text("Content")
        }
    }
}
