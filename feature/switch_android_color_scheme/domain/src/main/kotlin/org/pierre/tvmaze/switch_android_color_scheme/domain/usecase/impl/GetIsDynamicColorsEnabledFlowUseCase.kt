package org.pierre.tvmaze.switch_android_color_scheme.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.switch_android_color_scheme.domain.repository.AndroidColorSchemeRepository
import org.pierre.tvmaze.switch_android_color_scheme.domain.usecase.GetIsDynamicColorsEnabledFlow

internal class GetIsDynamicColorsEnabledFlowUseCase(
    private val repository: AndroidColorSchemeRepository,
) : GetIsDynamicColorsEnabledFlow {
    override fun invoke(): Flow<Boolean> = repository.getIsDynamicColorsEnabledFlow()
}
