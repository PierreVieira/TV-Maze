package org.pierre.tvmaze

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.pierre.tvmaze.core.preferences.LocalThemeOption
import org.pierre.tvmaze.core.preferences.LocalThemeOptionChange
import org.pierre.tvmaze.core.preferences.ThemePreference
import org.pierre.tvmaze.ui.navigation.presentation.RootAppNavHost
import org.pierre.tvmaze.ui.theme.AppTheme

@Composable
@Preview
fun App(
    getNavigationModifier: (onBack: () -> Unit) -> Modifier = { Modifier },
    getSpecificColors: @Composable ((isAppInDarkTheme: Boolean) -> ColorScheme?)? = null,
) {
    ProvideCompositionLocals(
        themePreference = ThemePreference.SYSTEM,
        onThemePreferenceChanged = {},
    ) {
        AppTheme(getSpecificColors = getSpecificColors) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                RootAppNavHost(
                    getNavigationModifier = getNavigationModifier
                )
            }
        }
    }
}

@Composable
private fun ProvideCompositionLocals(
    themePreference: ThemePreference,
    onThemePreferenceChanged: (ThemePreference) -> Unit,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalThemeOption provides themePreference,
        LocalThemeOptionChange provides onThemePreferenceChanged,
        content = content,
    )
}
