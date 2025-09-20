package org.pierre.tvmaze.ui.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.pierre.tvmaze.ui.navigation.domain.NavRoute

@Composable
fun RootAppNavHost(
    navHostController: NavHostController,
    switchPlatformColorSchemeComponent: @Composable (Modifier) -> Unit,
    getNavigationModifier: (onBack: () -> Unit) -> Modifier,
    extraRoute: (NavGraphBuilder) -> Unit,
) {
    NavHost(
        modifier = getNavigationModifier(navHostController::navigateUp),
        navController = navHostController,
        startDestination = NavRoute.Main,
        builder = {
            buildNavHost(
                navHostController = navHostController,
                switchPlatformColorSchemeComponent = switchPlatformColorSchemeComponent,
                getNavigationModifier = getNavigationModifier,
            )
            extraRoute(this)
        }
    )
}
