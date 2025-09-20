package org.pierre.tvmaze.search.data.mapper

import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition

interface SearchPositionPreferencesMapper {
    fun mapPositionToPreference(position: SearchBarPosition): String
    fun mapPreferenceToPosition(preference: String?): SearchBarPosition
}
