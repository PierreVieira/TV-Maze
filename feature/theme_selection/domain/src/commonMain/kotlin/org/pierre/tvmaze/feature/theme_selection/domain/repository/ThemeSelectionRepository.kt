package org.pierre.tvmaze.feature.theme_selection.domain.repository

import kotlinx.coroutines.flow.Flow
import org.pierre.core.theme.Theme

interface ThemeSelectionRepository {
    fun getThemeFlow(): Flow<Theme>
    suspend fun setTheme(theme: Theme)
}
