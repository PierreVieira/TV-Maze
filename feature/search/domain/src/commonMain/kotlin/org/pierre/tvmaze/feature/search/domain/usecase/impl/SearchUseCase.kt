package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.model.exception.EmptySearchQueryException
import org.pierre.tvmaze.feature.search.domain.model.SearchResultItemModel
import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.feature.search.domain.usecase.Search

internal class SearchUseCase(
    private val repository: SearchRepository
): Search {
    override suspend fun invoke(query: String): Result<List<SearchResultItemModel>> {
        val safeQuery = query.trim()
        return if (safeQuery.isEmpty()) {
            Result.failure(EmptySearchQueryException())
        } else {
            repository.search(safeQuery)
        }
    }
}
