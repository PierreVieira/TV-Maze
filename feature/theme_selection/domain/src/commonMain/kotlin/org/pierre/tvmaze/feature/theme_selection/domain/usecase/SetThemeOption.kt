package org.pierre.tvmaze.feature.theme_selection.domain.usecase

import org.pierre.core.model.theme.Theme

fun interface SetThemeOption {
    suspend operator fun invoke(theme: Theme)
}
