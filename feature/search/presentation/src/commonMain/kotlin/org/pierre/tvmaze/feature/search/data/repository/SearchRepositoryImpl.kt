package org.pierre.tvmaze.feature.search.data.repository

import org.pierre.tvmaze.feature.search.domain.model.SearchResultItemModel
import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository

internal class SearchRepositoryImpl: SearchRepository {
    override suspend fun search(query: String): Result<List<SearchResultItemModel>> = Result.success(emptyList())
}
