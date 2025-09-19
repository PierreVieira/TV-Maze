package org.pierre.tvmaze.switch_android_color_scheme.domain.usecase.impl

import org.pierre.tvmaze.switch_android_color_scheme.domain.repository.AndroidColorSchemeRepository
import org.pierre.tvmaze.switch_android_color_scheme.domain.usecase.SetIsDynamicColorsEnabled

internal class SetIsDynamicColorsEnabledUseCase(
    private val repository: AndroidColorSchemeRepository,
) : SetIsDynamicColorsEnabled {
    override suspend fun invoke(isEnabled: Boolean) {
        repository.setIsDynamicColorsEnabled(isEnabled)
    }
}
