package org.pierre.tvmaze.feature.search.domain.usecase

import org.pierre.tvmaze.feature.search.domain.model.SearchResultItemModel

fun interface Search {
    suspend operator fun invoke(query: String): Result<List<SearchResultItemModel>>
}
