package org.pierre.tvmaze.feature.main.presentation.navhost

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.pierre.tvmaze.feature.main.presentation.bottom_nav_route.favorites
import org.pierre.tvmaze.feature.main.presentation.bottom_nav_route.search
import org.pierre.tvmaze.feature.main.presentation.bottom_nav_route.themeSettings
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavRoute

@Composable
fun BottomNavHost(
    appNavHostController: NavHostController,
    switchPlatformColorSchemeComponent: @Composable (Modifier) -> Unit,
    bottomNavController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = bottomNavController,
        startDestination = BottomNavRoute.Search,
    ) {
        search(appNavHostController)
        favorites(appNavHostController)
        themeSettings(switchPlatformColorSchemeComponent)
    }
}

