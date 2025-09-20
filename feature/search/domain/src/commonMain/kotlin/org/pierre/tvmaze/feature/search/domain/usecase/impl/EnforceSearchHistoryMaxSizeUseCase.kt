package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel
import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.feature.search.domain.usecase.EnforceSearchHistoryMaxSize

internal class EnforceSearchHistoryMaxSizeUseCase(
    private val repository: SearchRepository
): EnforceSearchHistoryMaxSize {
    override suspend fun invoke() {
        val all: List<SearchHistoryItemModel> = repository.getAllSearches()
        if (all.size > MAX_RECENT_ITEMS) {
            val toDelete = all.sortedByDescending { it.timestamp }
                .drop(MAX_RECENT_ITEMS)
                .map { it.id }
            if (toDelete.isNotEmpty()) {
                repository.deleteSearchesByIds(toDelete)
            }
        }
    }

    companion object {
        private const val MAX_RECENT_ITEMS = 20
    }
}
