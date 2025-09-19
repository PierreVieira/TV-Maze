package org.pierre.tvmaze.feature.theme_selection.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import org.pierre.core.model.theme.Theme
import org.pierre.tvmaze.feature.theme_selection.domain.repository.ThemeSelectionRepository
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.GetThemeOptionFlowUseCase

internal class GetThemeOptionFlow(
    private val repository: ThemeSelectionRepository
): GetThemeOptionFlowUseCase {
    override operator fun invoke(): Flow<Theme> = repository.getThemeFlow()
}
