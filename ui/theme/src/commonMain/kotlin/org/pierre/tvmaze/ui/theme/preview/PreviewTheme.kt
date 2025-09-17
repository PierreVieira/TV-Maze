package org.pierre.tvmaze.ui.theme.preview

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.pierre.tvmaze.core.preferences.LocalThemeOption
import org.pierre.tvmaze.core.preferences.ThemePreference
import org.pierre.tvmaze.ui.theme.AppTheme

@Composable
fun PreviewTheme(currentTheme: ThemePreference, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalThemeOption provides currentTheme) {
        AppTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
                content()
            }
        }
    }
}
