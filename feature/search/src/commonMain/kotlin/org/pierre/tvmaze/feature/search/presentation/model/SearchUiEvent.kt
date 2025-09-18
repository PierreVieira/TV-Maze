package org.pierre.tvmaze.feature.search.presentation.model

sealed interface SearchUiEvent {
    data class OnQueryChange(val query: String) : SearchUiEvent
    data class OnExpandedChange(val expanded: Boolean) : SearchUiEvent
    data class OnSearch(val query: String) : SearchUiEvent
}
