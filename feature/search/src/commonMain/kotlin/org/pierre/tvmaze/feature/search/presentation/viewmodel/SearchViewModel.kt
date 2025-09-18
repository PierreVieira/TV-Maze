package org.pierre.tvmaze.feature.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.pierre.tvmaze.feature.search.presentation.model.SearchState
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent

class SearchViewModel: ViewModel() {
    private val _state = MutableStateFlow(
        SearchState(
            query = "",
            isExpanded = false,
            searchResults = emptyList()
        )
    )
    val state = _state.asStateFlow()

    fun onEvent(uiEvent: SearchUiEvent) {
        when(uiEvent) {
            is SearchUiEvent.OnExpandedChange -> _state.update {
                it.copy(isExpanded = uiEvent.expanded)
            }
            is SearchUiEvent.OnQueryChange -> _state.update {
                it.copy(query = uiEvent.query)
            }
            is SearchUiEvent.OnSearch -> _state.update {
                onSearch(uiEvent.query)
                it.copy(isExpanded = false)
            }
        }
    }

    private fun onSearch(query: String) {

    }
}
