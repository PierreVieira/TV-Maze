package org.pierre.tvmaze.ui.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.pierre.tvmaze.ui.navigation.domain.NavRoute

@Composable
fun RootAppNavHost(
    getNavigationModifier: (onBack: () -> Unit) -> Modifier = { Modifier },
) {
    val appNavController: NavHostController = rememberNavController()
    NavHost(
        modifier = getNavigationModifier(appNavController::navigateUp),
        navController = appNavController,
        startDestination = NavRoute.Main,
        builder = {
            buildNavHost(
                getNavigationModifier = getNavigationModifier
            )
        }
    )
}
