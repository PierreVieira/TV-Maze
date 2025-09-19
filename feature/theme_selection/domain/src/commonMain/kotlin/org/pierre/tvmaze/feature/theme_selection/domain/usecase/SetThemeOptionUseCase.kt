package org.pierre.tvmaze.feature.theme_selection.domain.usecase

import org.pierre.core.theme.Theme

interface SetThemeOptionUseCase {
    suspend operator fun invoke(theme: Theme)
}
