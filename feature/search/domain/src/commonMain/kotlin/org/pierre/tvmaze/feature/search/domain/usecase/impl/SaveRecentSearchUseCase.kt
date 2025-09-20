package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel
import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.feature.search.domain.usecase.SaveRecentSearch
import org.pierre.tvmaze.feature.search.domain.usecase.EnforceSearchHistoryMaxSize
import org.pierre.tvmaze.feature.search.domain.usecase.GetCurrentTime

class SaveRecentSearchUseCase(
    private val getCurrentTime: GetCurrentTime,
    private val enforceSearchHistoryMaxSize: EnforceSearchHistoryMaxSize,
    private val repository: SearchRepository,
) : SaveRecentSearch {
    override suspend fun invoke(query: String) {
        val timestamp = getCurrentTime()
        repository.run {
            getAllSearches().find {
                it.query.equals(query, ignoreCase = true)
            }?.let { current: SearchHistoryItemModel ->
                update(current.copy(timestamp))
            } ?: insert(query, timestamp)
        }
        enforceSearchHistoryMaxSize()
    }
}
