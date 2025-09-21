package org.pierre.tvmaze.search.data.repository

import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.pierre.tvmaze.core.room_provider.dao.LastSearchesDao
import org.pierre.tvmaze.core.room_provider.entity.SearchHistoryItemEntity
import org.pierre.tvmaze.dto.MediaResultDto
import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel
import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.mapper.MediaItemModelMapper
import org.pierre.tvmaze.model.common.MediaItemCard
import org.pierre.tvmaze.network.data.handler.RequestHandler
import org.pierre.tvmaze.search.data.mapper.SearchHistoryItemMapper

internal class SearchRepositoryImpl(
    private val requestHandler: RequestHandler,
    private val mediaItemModelMapper: MediaItemModelMapper,
    private val searchHistoryItemMapper: SearchHistoryItemMapper,
    private val lastSearchesDao: LastSearchesDao,
) : SearchRepository {

    override suspend fun search(query: String): Result<List<MediaItemCard>> =
        requestHandler.call<List<MediaResultDto>> {
            get("/search/shows?q=$query")
        }.map { shows ->
            shows.mapNotNull(mediaItemModelMapper::map)
        }

    override suspend fun getAllSearches(): List<SearchHistoryItemModel> =
        lastSearchesDao.getAll().map(searchHistoryItemMapper::mapToDomain)

    override suspend fun deleteSearchesByIds(ids: List<Long>) {
        lastSearchesDao.deleteByIds(ids)
    }

    override suspend fun insert(query: String, timestamp: Long): Result<Unit> {
        val insertId =
            lastSearchesDao.insert(SearchHistoryItemEntity(query = query, timestamp = timestamp))
        return if (insertId == NOT_FOUND_ID) {
            Result.failure(IllegalArgumentException("Item already exists"))
        } else {
            Result.success(Unit)
        }
    }

    override suspend fun update(current: SearchHistoryItemModel) {
        lastSearchesDao.update(searchHistoryItemMapper.mapToEntity(current))
    }

    override fun getRecentSearchesFlow(): Flow<List<SearchHistoryItemModel>> =
        lastSearchesDao.getAllAsFlow().map { entities: List<SearchHistoryItemEntity> ->
            entities.map(searchHistoryItemMapper::mapToDomain)
        }

    override suspend fun clearRecentSearches() {
        lastSearchesDao.clear()
    }

    companion object {
        private const val NOT_FOUND_ID = -1L
    }
}
