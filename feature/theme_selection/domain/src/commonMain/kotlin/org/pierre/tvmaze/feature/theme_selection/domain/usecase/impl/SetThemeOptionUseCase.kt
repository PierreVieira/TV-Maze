package org.pierre.tvmaze.feature.theme_selection.domain.usecase.impl

import org.pierre.core.model.theme.Theme
import org.pierre.tvmaze.feature.theme_selection.domain.repository.ThemeSelectionRepository
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.SetThemeOption

internal class SetThemeOptionUseCase(
    private val repository: ThemeSelectionRepository,
): SetThemeOption {

    override suspend operator fun invoke(theme: Theme) {
        repository.setTheme(theme)
    }
}
