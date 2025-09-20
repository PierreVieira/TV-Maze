package org.pierre.tvmaze.feature.search.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel
import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.feature.search.domain.usecase.GetSearchHistoryFlow

internal class GetSearchHistoryFlowUseCase(
    private val repository: SearchRepository,
): GetSearchHistoryFlow {
    override fun invoke(): Flow<List<SearchHistoryItemModel>> =
        repository.getRecentSearchesFlow()
}
