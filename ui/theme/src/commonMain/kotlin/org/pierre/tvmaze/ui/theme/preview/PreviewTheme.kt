package org.pierre.tvmaze.ui.theme.preview

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.pierre.core.theme.Theme
import org.pierre.tvmaze.ui.theme.AppTheme
import org.pierre.tvmaze.ui.theme.utils.LocalThemeOption

@Composable
fun PreviewTheme(currentTheme: Theme, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalThemeOption provides currentTheme) {
        AppTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
                content()
            }
        }
    }
}
