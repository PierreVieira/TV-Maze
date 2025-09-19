package org.pierre.tvmaze.ui.navigation.presentation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.tvmaze.ui.utils.ActionCollector
import org.pierre.tvmaze.feature.main.presentation.MainScreen
import org.pierre.tvmaze.feature.main.presentation.MainScreenViewModel
import org.pierre.tvmaze.feature.main.presentation.model.MainScreenUiAction
import org.pierre.tvmaze.feature.main.presentation.navhost.BottomNavHost
import org.pierre.tvmaze.ui.navigation.domain.NavRoute

fun NavGraphBuilder.mainScreen(
    switchPlatformColorSchemeComponent: @Composable (Modifier) -> Unit,
    getNavigationModifier: (onBack: () -> Unit) -> Modifier,
) {
    composable<NavRoute.Main> { mainBackStackEntry: NavBackStackEntry ->
        val bottomNavController: NavHostController = rememberNavController()
        val bottomNavBackStackEntry by bottomNavController.currentBackStackEntryAsState()
        val mainViewModel: MainScreenViewModel = koinViewModel()
        val goToBottomNavRoute = bottomNavController::goToBottomNavRoute
        ActionCollector(mainViewModel.uiAction) { uiAction ->
            when (uiAction) {
                is MainScreenUiAction.NavigateToBottomRoute -> goToBottomNavRoute(uiAction.route)
            }
        }
        MainScreen(
            currentDestination = bottomNavBackStackEntry?.destination,
            bottomNavigationModels = mainViewModel.bottomNavigationItemModels,
            onEvent = mainViewModel::onEvent,
        ) {
            BottomNavHost(
                modifier = getNavigationModifier(bottomNavController::navigateUp),
                navController = bottomNavController,
                switchPlatformColorSchemeComponent = switchPlatformColorSchemeComponent,
            )
        }
    }
}

private fun NavHostController.goToBottomNavRoute(route: Any) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
