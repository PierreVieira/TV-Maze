package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.feature.search.domain.usecase.DeleteSearchHistoryItem

internal class DeleteSearchHistoryItemUseCase(
    private val repository: SearchRepository
): DeleteSearchHistoryItem {
    override suspend fun invoke(id: Long) {
        repository.deleteSearchesByIds(listOf(id))
    }
}
