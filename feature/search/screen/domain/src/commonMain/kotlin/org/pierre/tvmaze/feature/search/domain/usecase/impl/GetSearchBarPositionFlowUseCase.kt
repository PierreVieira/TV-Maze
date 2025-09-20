package org.pierre.tvmaze.feature.search.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.domain.repository.SearchBarRepository
import org.pierre.tvmaze.feature.search.domain.usecase.GetSearchBarPositionFlow

internal class GetSearchBarPositionFlowUseCase(
    private val repository: SearchBarRepository,
): GetSearchBarPositionFlow {
    override fun invoke(): Flow<SearchBarPosition> = repository.getSearchBarPositionFlow()
}
