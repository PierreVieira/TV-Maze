package org.pierre.tvmaze.ui.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import org.pierre.tvmaze.ui.navigation.presentation.graph.deleteSearchItem
import org.pierre.tvmaze.ui.navigation.presentation.graph.mainScreen
import org.pierre.tvmaze.ui.navigation.presentation.graph.deleteAllSearch

fun NavGraphBuilder.buildNavHost(
    navHostController: NavHostController,
    switchPlatformColorSchemeComponent: @Composable (Modifier) -> Unit,
    getNavigationModifier: (onBack: () -> Unit) -> Modifier,
) {
    mainScreen(
        appNavController = navHostController,
        switchPlatformColorSchemeComponent = switchPlatformColorSchemeComponent,
        getNavigationModifier = getNavigationModifier,
    )
    deleteSearchItem(navHostController)
    deleteAllSearch(navHostController)
}
