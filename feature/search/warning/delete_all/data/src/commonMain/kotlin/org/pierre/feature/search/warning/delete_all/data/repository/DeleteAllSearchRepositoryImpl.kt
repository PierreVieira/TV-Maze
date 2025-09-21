package org.pierre.feature.search.warning.delete_all.data.repository

import org.pierre.feature.search.warning.delete_all.domain.repository.DeleteAllSearchRepository
import org.pierre.tvmaze.core.room_provider.dao.LastSearchesDao

internal class DeleteAllSearchRepositoryImpl(
    private val lastSearchesDao: LastSearchesDao,
): DeleteAllSearchRepository {
    override suspend fun deleteAll() {
        lastSearchesDao.clear()
    }
}
