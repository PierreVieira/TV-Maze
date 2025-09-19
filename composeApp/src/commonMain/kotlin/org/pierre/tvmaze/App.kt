package org.pierre.tvmaze

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.core.theme.Theme
import org.pierre.tvmaze.presentation.AppViewModel
import org.pierre.tvmaze.ui.navigation.presentation.RootAppNavHost
import org.pierre.tvmaze.ui.theme.AppTheme
import org.pierre.tvmaze.ui.theme.utils.LocalThemeOption

@Composable
@Preview
fun App(
    getNavigationModifier: (onBack: () -> Unit) -> Modifier = { Modifier },
    getSpecificColors: @Composable ((isAppInDarkTheme: Boolean) -> ColorScheme?)? = null,
) {
    val viewModel: AppViewModel = koinViewModel()
    val themeState by viewModel.themeState.collectAsState()
    ProvideCompositionLocals(
        theme = themeState,
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
    theme: Theme,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalThemeOption provides theme,
        content = content,
    )
}
