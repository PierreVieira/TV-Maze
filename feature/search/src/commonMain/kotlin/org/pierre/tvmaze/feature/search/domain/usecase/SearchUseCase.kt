package org.pierre.tvmaze.feature.search.domain.usecase

import org.pierre.tvmaze.feature.search.domain.model.SearchResultItemModel

interface SearchUseCase {
    suspend operator fun invoke(query: String): Result<List<SearchResultItemModel>>
}
