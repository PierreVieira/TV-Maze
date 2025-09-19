package org.pierre.tvmaze.ui.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import org.pierre.tvmaze.ui.navigation.presentation.graph.mainScreen

fun NavGraphBuilder.buildNavHost(
    switchPlatformColorSchemeComponent: @Composable (Modifier) -> Unit,
    getNavigationModifier: (onBack: () -> Unit) -> Modifier,
) {
    mainScreen(
        switchPlatformColorSchemeComponent = switchPlatformColorSchemeComponent,
        getNavigationModifier = getNavigationModifier,
    )
}
