package org.pierre.tvmaze.feature.search.domain.repository

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel
import org.pierre.tvmaze.model.common.MediaItemModel

interface SearchRepository {
    fun getRecentSearchesFlow(): Flow<List<SearchHistoryItemModel>>
    suspend fun insert(query: String, timestamp: Long): Result<Unit>
    suspend fun search(query: String): Result<List<MediaItemModel>>
    suspend fun getAllSearches(): List<SearchHistoryItemModel>
    suspend fun deleteSearchesByIds(ids: List<Long>)
    suspend fun update(current: SearchHistoryItemModel)
    suspend fun clearRecentSearches()
}
