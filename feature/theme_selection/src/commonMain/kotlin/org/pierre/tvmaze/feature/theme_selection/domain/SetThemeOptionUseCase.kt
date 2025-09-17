package org.pierre.tvmaze.feature.theme_selection.domain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import org.pierre.tvmaze.core.preferences.PreferencesKeys
import org.pierre.tvmaze.core.preferences.PreferencesValue
import org.pierre.tvmaze.core.preferences.ThemePreference

class SetThemeOptionUseCase (
    private val dataStore: DataStore<Preferences>,
) {

    suspend operator fun invoke(theme: ThemePreference) {
        dataStore.edit {
            it[stringPreferencesKey(PreferencesKeys.THEME)] = when (theme) {
                ThemePreference.LIGHT -> PreferencesValue.LIGHT_THEME
                ThemePreference.DARK -> PreferencesValue.DARK_THEME
                ThemePreference.SYSTEM -> PreferencesValue.SYSTEM_THEME
            }
        }
    }
}
