package org.pierre.tvmaze.feature.search.presentation.factory

import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel
import org.pierre.tvmaze.feature.search.presentation.model.SearchContent

interface SearchHistoryContentFactory {
    fun create(history: List<SearchHistoryItemModel>, query: String): SearchContent
}
