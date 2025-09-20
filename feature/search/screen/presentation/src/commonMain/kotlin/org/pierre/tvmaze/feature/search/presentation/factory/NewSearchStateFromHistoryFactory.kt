package org.pierre.tvmaze.feature.search.presentation.factory

import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel
import org.pierre.tvmaze.feature.search.presentation.model.SearchState

interface NewSearchStateFromHistoryFactory {
    fun create(
        searchHistory: List<SearchHistoryItemModel>,
        currentState: SearchState
    ): SearchState
}
