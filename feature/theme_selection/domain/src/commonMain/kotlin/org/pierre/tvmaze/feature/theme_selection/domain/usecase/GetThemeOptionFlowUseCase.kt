package org.pierre.tvmaze.feature.theme_selection.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.pierre.core.model.theme.Theme

interface GetThemeOptionFlowUseCase {
    operator fun invoke(): Flow<Theme>
}
