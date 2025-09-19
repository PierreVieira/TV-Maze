package org.pierre.tvmaze.switch_android_color_scheme.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.pierre.tvmaze.core.utils.orTrue
import org.pierre.tvmaze.switch_android_color_scheme.domain.repository.AndroidColorSchemeRepository

internal class AndroidColorSchemeRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : AndroidColorSchemeRepository {

    override fun getIsDynamicColorsEnabledFlow(): Flow<Boolean> = dataStore.data.map {
        it[booleanPreferencesKey(IS_DYNAMIC_COLORS_ENABLED_KEY)].orTrue()
    }

    override suspend fun setIsDynamicColorsEnabled(isEnabled: Boolean) {
        dataStore.edit {
            it[booleanPreferencesKey(IS_DYNAMIC_COLORS_ENABLED_KEY)] = isEnabled
        }
    }

    companion object {
        private const val IS_DYNAMIC_COLORS_ENABLED_KEY = "is_dynamic_colors_enabled"
    }
}
