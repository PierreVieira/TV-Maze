package org.pierre.tvmaze.feature.theme_selection.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import org.pierre.core.model.theme.Theme
import org.pierre.tvmaze.feature.theme_selection.domain.repository.ThemeSelectionRepository
import org.pierre.tvmaze.feature.theme_selection.domain.usecase.GetThemeOptionFlow

internal class GetThemeOptionFlowUseCase(
    private val repository: ThemeSelectionRepository
): GetThemeOptionFlow {
    override operator fun invoke(): Flow<Theme> = repository.getThemeFlow()
}
