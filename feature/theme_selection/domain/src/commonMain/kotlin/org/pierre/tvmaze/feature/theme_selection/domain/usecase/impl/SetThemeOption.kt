package org.pierre.tvmaze.feature.theme_selection.domain.usecase.impl

import org.pierre.core.model.theme.Theme
import org.pierre.tvmaze.feature.theme_selection.domain.repository.ThemeSelectionRepository
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.SetThemeOptionUseCase

internal class SetThemeOption(
    private val repository: ThemeSelectionRepository,
): SetThemeOptionUseCase {

    override suspend operator fun invoke(theme: Theme) {
        repository.setTheme(theme)
    }
}
