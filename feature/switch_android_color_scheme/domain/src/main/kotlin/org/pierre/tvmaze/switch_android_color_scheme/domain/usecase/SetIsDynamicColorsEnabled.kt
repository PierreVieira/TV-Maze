package org.pierre.tvmaze.switch_android_color_scheme.domain.usecase

interface SetIsDynamicColorsEnabled {
    suspend operator fun invoke(isEnabled: Boolean)
}
