package org.pierre.tvmaze.feature.search.domain.repository

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition

interface SearchBarRepository {
    suspend fun saveNewSearchBarPosition(position: SearchBarPosition)
    fun getSearchBarPositionFlow(): Flow<SearchBarPosition>
}
