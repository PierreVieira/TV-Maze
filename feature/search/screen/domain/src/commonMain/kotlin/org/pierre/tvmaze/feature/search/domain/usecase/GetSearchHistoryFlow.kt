package org.pierre.tvmaze.feature.search.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel

interface GetSearchHistoryFlow {
    operator fun invoke(): Flow<List<SearchHistoryItemModel>>
}
