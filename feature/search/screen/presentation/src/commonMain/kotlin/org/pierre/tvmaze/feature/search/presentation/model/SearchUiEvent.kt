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
    data class OnHistoryItemClick(val itemName: String) : SearchUiEvent
    data class OnHistoryItemLongClick(val name: String, val id: Long) : SearchUiEvent
    data class OnHistoryItemDeleteClick(val name: String, val id: Long) : SearchUiEvent
    data class OnSearchResultItemClick(val id: Long) : SearchUiEvent
    data class OnFavoriteSearchResultItemClick(val id: Long) : SearchUiEvent
}
