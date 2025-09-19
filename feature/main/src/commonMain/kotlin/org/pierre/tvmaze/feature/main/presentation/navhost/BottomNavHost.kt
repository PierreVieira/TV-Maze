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
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = BottomNavRoute.Search,
    ) {
        search()
        favorites()
        themeSettings()
    }
}

