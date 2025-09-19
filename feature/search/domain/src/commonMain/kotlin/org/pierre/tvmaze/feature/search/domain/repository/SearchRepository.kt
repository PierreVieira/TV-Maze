package org.pierre.tvmaze.feature.search.domain.repository

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.domain.model.SearchResultItemModel

interface SearchRepository {
    suspend fun search(query: String): Result<List<SearchResultItemModel>>
    suspend fun saveNewSearchBarPosition(position: SearchBarPosition)
    fun getSearchBarPositionFlow(): Flow<SearchBarPosition>
}
