package org.pierre.tvmaze.feature.search.domain.repository

import org.pierre.tvmaze.feature.search.domain.model.SearchResultItemModel

interface SearchRepository {
    suspend fun search(query: String): Result<List<SearchResultItemModel>>
}
