package org.pierre.tvmaze.material_you.domain.model

import org.pierre.tvmaze.material_you.domain.usecase.GetIsDynamicColorsEnabledFlow
import org.pierre.tvmaze.material_you.domain.usecase.SetIsDynamicColorsEnabled

data class MaterialYouUseCases(
    val getIsDynamicColorsEnabledFlow: GetIsDynamicColorsEnabledFlow,
    val setIsDynamicColorsEnabled: SetIsDynamicColorsEnabled,
)
