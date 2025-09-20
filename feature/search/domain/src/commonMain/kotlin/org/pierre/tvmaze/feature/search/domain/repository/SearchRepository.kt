package org.pierre.tvmaze.feature.search.domain.repository

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.model.common.ShowItemModel

interface SearchRepository {
    suspend fun search(query: String): Result<List<ShowItemModel>>
    suspend fun saveNewSearchBarPosition(position: SearchBarPosition)
    fun getSearchBarPositionFlow(): Flow<SearchBarPosition>
}
