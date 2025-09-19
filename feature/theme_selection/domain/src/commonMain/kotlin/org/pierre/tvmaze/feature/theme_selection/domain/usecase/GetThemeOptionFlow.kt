package org.pierre.tvmaze.feature.theme_selection.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.pierre.core.model.theme.Theme

fun interface GetThemeOptionFlow {
    operator fun invoke(): Flow<Theme>
}
