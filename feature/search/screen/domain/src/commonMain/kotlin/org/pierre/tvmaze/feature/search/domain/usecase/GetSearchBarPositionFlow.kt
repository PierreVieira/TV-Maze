package org.pierre.tvmaze.feature.search.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition

interface GetSearchBarPositionFlow {
    operator fun invoke(): Flow<SearchBarPosition>
}
