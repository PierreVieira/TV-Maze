package org.pierre.tvmaze.switch_android_color_scheme.domain.repository

import kotlinx.coroutines.flow.Flow

interface AndroidColorSchemeRepository {
    fun getIsDynamicColorsEnabledFlow(): Flow<Boolean>
    suspend fun setIsDynamicColorsEnabled(isEnabled: Boolean)
}
