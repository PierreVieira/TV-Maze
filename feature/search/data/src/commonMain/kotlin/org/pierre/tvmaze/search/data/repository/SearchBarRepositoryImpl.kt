package org.pierre.tvmaze.search.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.domain.repository.SearchBarRepository
import org.pierre.tvmaze.search.data.mapper.SearchPositionPreferencesMapper

class SearchBarRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val searchPositionMapper: SearchPositionPreferencesMapper,
): SearchBarRepository {
    override suspend fun saveNewSearchBarPosition(position: SearchBarPosition) {
        dataStore.edit {
            it[stringPreferencesKey(SEARCH_BAR_POSITION)] =
                searchPositionMapper.mapPositionToPreference(position)
        }
    }

    override fun getSearchBarPositionFlow(): Flow<SearchBarPosition> = dataStore.data.map { value ->
        searchPositionMapper.mapPreferenceToPosition(
            preference = value[stringPreferencesKey(SEARCH_BAR_POSITION)]
        )
    }

    companion object {
        private const val SEARCH_BAR_POSITION = "SEARCH_BAR_POSITION"
    }
}
