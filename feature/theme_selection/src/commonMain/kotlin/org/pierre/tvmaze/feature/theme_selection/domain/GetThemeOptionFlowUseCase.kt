package org.pierre.tvmaze.feature.theme_selection.domain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.pierre.tvmaze.core.preferences.PreferencesKeys
import org.pierre.tvmaze.core.preferences.PreferencesValue
import org.pierre.tvmaze.core.preferences.ThemePreference

class GetThemeOptionFlowUseCase(
    private val dataStore: DataStore<Preferences>
) {
    operator fun invoke(): Flow<ThemePreference> = dataStore.data.map { value ->
        val theme = value[stringPreferencesKey(PreferencesKeys.THEME)]
        when (theme) {
            PreferencesValue.LIGHT_THEME -> ThemePreference.LIGHT
            PreferencesValue.DARK_THEME -> ThemePreference.DARK
            else -> ThemePreference.SYSTEM
        }
    }
}
