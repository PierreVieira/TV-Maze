package org.pierre.tvmaze.feature.search.presentation.model

sealed interface SearchUiEvent {
    data class OnQueryChange(val query: String) : SearchUiEvent
    data object OnSearch : SearchUiEvent
    data object OnClearClick : SearchUiEvent
    data object OnSearchIconClick : SearchUiEvent
    data object OnMoreOptionsClick : SearchUiEvent
    data object OnDismissMenuClick : SearchUiEvent
    data object OnChangeSearchBarPositionClick : SearchUiEvent
    data object OnDeleteHistoryClick : SearchUiEvent
}
