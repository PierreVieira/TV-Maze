package org.pierre.tvmaze.feature.search.presentation.factory.impl

import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel
import org.pierre.tvmaze.feature.search.presentation.factory.SearchHistoryContentFactory
import org.pierre.tvmaze.feature.search.presentation.model.SearchContent

class SearchHistoryContentFactoryImpl : SearchHistoryContentFactory {

    override fun create(
        history: List<SearchHistoryItemModel>,
        query: String,
    ): SearchContent = when {
        history.isEmpty() -> SearchContent.Error.NoHistory

        query.isBlank() -> {
            // Return full history, but clear any previous highlight range
            val cleared = history
                .sortedByDescending { it.timestamp }
                .map { it.copy(searchedRange = IntRange.EMPTY) }
            SearchContent.History(cleared)
        }

        else -> {
            val safeQuery = query.trim()
            val filteredWithRanges = history
                .sortedByDescending { it.timestamp }
                .mapNotNull { item ->
                    val start = item.query.indexOf(safeQuery, ignoreCase = true)
                    if (start >= 0) {
                        val end = start + safeQuery.length - 1 // IntRange is inclusive
                        item.copy(searchedRange = start..end)
                    } else {
                        null
                    }
                }

            if (filteredWithRanges.isEmpty()) {
                SearchContent.Error.NoHistoryForSpecificQuery
            } else {
                SearchContent.History(filteredWithRanges)
            }
        }
    }
}
