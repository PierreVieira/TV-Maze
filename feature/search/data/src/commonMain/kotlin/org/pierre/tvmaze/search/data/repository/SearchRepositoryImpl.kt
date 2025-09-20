package org.pierre.tvmaze.search.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.pierre.tvmaze.core.room_provider.dao.LastSearchesDao
import org.pierre.tvmaze.core.room_provider.entity.LastSearchEntity
import org.pierre.tvmaze.dto.ShowResultDto
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.mapper.ShowItemModelMapper
import org.pierre.tvmaze.model.common.ShowItemModel
import org.pierre.tvmaze.network.data.handler.RequestHandler
import org.pierre.tvmaze.search.data.mapper.SearchPositionPreferencesMapper
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

internal class SearchRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val searchPositionMapper: SearchPositionPreferencesMapper,
    private val requestHandler: RequestHandler,
    private val mapper: ShowItemModelMapper,
    private val lastSearchesDao: LastSearchesDao,
) : SearchRepository {

    override suspend fun search(query: String): Result<List<ShowItemModel>> =
        requestHandler.call<List<ShowResultDto>> {
            get("/search/shows?q=$query")
        }.map { shows ->
            shows.mapNotNull(mapper::map)
        }

    override suspend fun saveNewSearchBarPosition(position: SearchBarPosition) {
        dataStore.edit {
            it[stringPreferencesKey(SEARCH_BAR_POSITION)] =
                searchPositionMapper.mapPositionToPreference(position)
        }
    }

    override fun getSearchBarPositionFlow(): Flow<SearchBarPosition> = dataStore.data.map { value ->
        searchPositionMapper.mapPreferenceToPosition(value[stringPreferencesKey(SEARCH_BAR_POSITION)])
    }

    // Recent searches
    @OptIn(ExperimentalTime::class)
    override suspend fun addRecentSearch(query: String) {
        lastSearchesDao.upsertByQuery(
            query = query.trim(),
            timestamp = Clock.System.now().epochSeconds,
            maxItems = MAX_RECENT_ITEMS,
        )
    }

    override fun observeRecentSearches(): Flow<List<String>> =
        lastSearchesDao.observeAll().map { list: List<LastSearchEntity> ->
            list.map { it.query }
        }

    override suspend fun clearRecentSearches() {
        lastSearchesDao.clear()
    }

    companion object {
        private const val SEARCH_BAR_POSITION = "SEARCH_BAR_POSITION"
        private const val MAX_RECENT_ITEMS = 15
    }
}
