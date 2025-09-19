package org.pierre.tvmaze.switch_android_color_scheme.domain.model

import org.pierre.tvmaze.switch_android_color_scheme.domain.usecase.GetIsDynamicColorsEnabledFlow
import org.pierre.tvmaze.switch_android_color_scheme.domain.usecase.SetIsDynamicColorsEnabled

data class AndroidColorSchemeUseCases(
    val getIsDynamicColorsEnabledFlow: GetIsDynamicColorsEnabledFlow,
    val setIsDynamicColorsEnabled: SetIsDynamicColorsEnabled,
)
