package org.pierre.tvmaze.feature.search.presentation.model

import org.pierre.tvmaze.feature.search.domain.model.error.SearchErrorType

sealed interface SearchUiAction {
    data class ShowError(val errorType: SearchErrorType): SearchUiAction
}
