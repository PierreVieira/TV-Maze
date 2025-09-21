package org.pierre.tvmaze.feature.search.presentation.model

import org.pierre.tvmaze.feature.search.domain.model.error.SearchErrorType

sealed interface SearchUiAction {
    data class ShowError(val errorType: SearchErrorType): SearchUiAction
    data class NavigateToDeleteSearchHistoryItem(val id: Long, val name: String): SearchUiAction
    data object NavigateToDeleteAllSearchHistory: SearchUiAction
    data class NavigateToMediaDetails(val id: Long): SearchUiAction
}
