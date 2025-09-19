package org.pierre.tvmaze.feature.theme_selection.domain.usecase

import org.pierre.core.model.theme.Theme

interface SetThemeOptionUseCase {
    suspend operator fun invoke(theme: Theme)
}
