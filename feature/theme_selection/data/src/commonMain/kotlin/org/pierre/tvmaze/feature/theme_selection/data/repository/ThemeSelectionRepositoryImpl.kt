package org.pierre.tvmaze.feature.theme_selection.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.pierre.core.model.theme.Theme
import org.pierre.tvmaze.feature.theme_selection.data.mapper.ThemePreferenceMapper
import org.pierre.tvmaze.feature.theme_selection.domain.repository.ThemeSelectionRepository

internal class ThemeSelectionRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val mapper: ThemePreferenceMapper
): ThemeSelectionRepository {
    override fun getThemeFlow(): Flow<Theme> = dataStore.data.map { value ->
        mapper.mapPreferenceToModel(value[stringPreferencesKey(THEME)])
    }

    override suspend fun setTheme(theme: Theme) {
        dataStore.edit {
            it[stringPreferencesKey(THEME)] = mapper.mapModelToPreference(theme)
        }
    }

    companion object {
        private const val THEME = "theme"
    }
}
