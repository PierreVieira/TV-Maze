package org.pierre.tvmaze.feature.theme_selection.domain.usecase.impl

import org.pierre.core.theme.Theme
import org.pierre.tvmaze.feature.theme_selection.domain.repository.ThemeSelectionRepository
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.SetThemeOptionUseCase

class SetThemeOption(
    private val repository: ThemeSelectionRepository,
): SetThemeOptionUseCase {

    override suspend operator fun invoke(theme: Theme) {
        repository.setTheme(theme)
    }
}
