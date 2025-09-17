package org.pierre.tvmaze.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import org.pierre.tvmaze.core.preferences.LocalThemeOption
import org.pierre.tvmaze.core.preferences.ThemePreference

@Composable
fun AppTheme(
    getSpecificColors: @Composable ((Boolean) -> ColorScheme?)? = null,
    content: @Composable () -> Unit,
) {
    val isDarkTheme = isAppInDarkTheme()
    MaterialTheme(
        colorScheme = getSpecificColors?.invoke(isDarkTheme) ?: getColorScheme(isDarkTheme),
        content = content
    )
}

@Composable
fun isAppInDarkTheme(): Boolean = when(LocalThemeOption.current) {
    ThemePreference.LIGHT -> false
    ThemePreference.DARK -> true
    ThemePreference.SYSTEM -> isSystemInDarkTheme()
}

@Composable
private fun getColorScheme(isDarkTheme: Boolean): ColorScheme = if (isDarkTheme) {
    darkColorScheme()
} else {
    lightColorScheme()
}
