package org.pierre.tvmaze.switch_android_color_scheme.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetIsDynamicColorsEnabledFlow {
    operator fun invoke(): Flow<Boolean>
}
