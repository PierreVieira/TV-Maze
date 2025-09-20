package org.pierre.tvmaze.search.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.pierre.tvmaze.dto.ShowResultDto
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.mapper.ShowItemModelMapper
import org.pierre.tvmaze.model.common.ShowItemModel
import org.pierre.tvmaze.network.data.handler.RequestHandler
import org.pierre.tvmaze.search.data.mapper.SearchPositionPreferencesMapper

internal class SearchRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val searchPositionMapper: SearchPositionPreferencesMapper,
    private val requestHandler: RequestHandler,
    private val mapper: ShowItemModelMapper,
): SearchRepository {

    override suspend fun search(query: String): Result<List<ShowItemModel>> =
        requestHandler.call<List<ShowResultDto>> {
            get("/search/shows?q=$query")
        }.map { shows ->
            shows.mapNotNull(mapper::map)
        }

    override suspend fun saveNewSearchBarPosition(position: SearchBarPosition) {
        dataStore.edit {
            it[stringPreferencesKey(SEARCH_BAR_POSITION)] = searchPositionMapper.mapPositionToPreference(position)
        }
    }

    override fun getSearchBarPositionFlow(): Flow<SearchBarPosition> = dataStore.data.map { value ->
        searchPositionMapper.mapPreferenceToPosition(value[stringPreferencesKey(SEARCH_BAR_POSITION)])
    }

    companion object {
        private const val SEARCH_BAR_POSITION = "SEARCH_BAR_POSITION"
    }
}
