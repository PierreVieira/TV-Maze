package org.pierre.tvmaze.material_you.domain.repository

import kotlinx.coroutines.flow.Flow

interface MaterialYouRepository {
    fun getIsDynamicColorsEnabledFlow(): Flow<Boolean>
    suspend fun setIsDynamicColorsEnabled(isEnabled: Boolean)
}
