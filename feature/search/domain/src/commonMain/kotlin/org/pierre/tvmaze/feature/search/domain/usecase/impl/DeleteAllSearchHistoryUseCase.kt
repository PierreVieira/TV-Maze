package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.feature.search.domain.usecase.DeleteAllSearchHistory

internal class DeleteAllSearchHistoryUseCase(
    private val repository: SearchRepository
): DeleteAllSearchHistory {
    override suspend fun invoke() {
        repository.run {
            deleteSearchesByIds(getAllSearches().map { it.id })
        }
    }
}
