package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.model.exception.EmptySearchQueryException
import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.feature.search.domain.usecase.SaveRecentSearch
import org.pierre.tvmaze.feature.search.domain.usecase.Search
import org.pierre.tvmaze.model.common.ShowItemModel

internal class SearchUseCase(
    private val repository: SearchRepository,
    private val saveRecentSearch: SaveRecentSearch,
): Search {
    override suspend fun invoke(query: String): Result<List<ShowItemModel>> {
        val safeQuery = query.trim()
        return if (safeQuery.isEmpty()) {
            Result.failure(EmptySearchQueryException())
        } else {
            repository.search(safeQuery.replace(" ", "%20")).also { saveRecentSearch(safeQuery) }
        }
    }
}
