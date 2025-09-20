package org.pierre.feature.search.warning.delete_item.data.repository

import org.pierre.feature.search.warning.delete_item.domain.repository.DeleteItemSearchRepository
import org.pierre.tvmaze.core.room_provider.dao.LastSearchesDao

internal class DeleteItemSearchRepositoryImpl(
    private val lastSearchesDao: LastSearchesDao,
): DeleteItemSearchRepository {
    override suspend fun deleteSearchItem(id: Long) {
        lastSearchesDao.deleteByIds(listOf(id))
    }
}
