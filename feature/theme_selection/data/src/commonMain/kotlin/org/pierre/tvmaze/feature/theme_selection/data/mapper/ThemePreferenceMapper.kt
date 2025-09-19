package org.pierre.tvmaze.feature.theme_selection.data.mapper

import org.pierre.core.model.theme.Theme

interface ThemePreferenceMapper {
    fun mapPreferenceToModel(preference: String?): Theme
    fun mapModelToPreference(theme: Theme): String
}
