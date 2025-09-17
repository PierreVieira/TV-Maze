package org.pierre.tvmaze.core.preferences

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalThemeOption: ProvidableCompositionLocal<ThemePreference> = compositionLocalOf {
    ThemePreference.SYSTEM
}

val LocalThemeOptionChange = compositionLocalOf<(ThemePreference) -> Unit> {
    throw IllegalStateException("LocalThemeOptionChange not provided")
}
