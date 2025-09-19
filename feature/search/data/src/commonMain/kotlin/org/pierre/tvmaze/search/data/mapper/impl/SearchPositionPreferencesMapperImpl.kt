package org.pierre.tvmaze.search.data.mapper.impl

import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.search.data.mapper.SearchPositionPreferencesMapper

class SearchPositionPreferencesMapperImpl : SearchPositionPreferencesMapper {
    override fun mapPositionToPreference(position: SearchBarPosition): String = when (position) {
        SearchBarPosition.TOP -> TOP_POSITION
        SearchBarPosition.BOTTOM -> BOTTOM_POSITION
    }

    override fun mapPreferenceToPosition(preference: String?): SearchBarPosition =
        if (preference == BOTTOM_POSITION) {
            SearchBarPosition.BOTTOM
        } else {
            SearchBarPosition.TOP
        }

    companion object {
        private const val TOP_POSITION = "TOP"
        private const val BOTTOM_POSITION = "BOTTOM"
    }
}
