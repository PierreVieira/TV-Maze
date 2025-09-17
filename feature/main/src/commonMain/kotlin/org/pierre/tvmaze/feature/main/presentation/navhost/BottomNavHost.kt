package org.pierre.tvmaze.feature.main.presentation.navhost

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavRoute

@Composable
fun BottomNavHost(
    paddingValues: PaddingValues,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        navController = navController,
        startDestination = BottomNavRoute.Search,
    ) {
        composable<BottomNavRoute.Search> {
            Text("Search")
        }
        composable<BottomNavRoute.Favorites> {
            Text("Favorites")
        }
        composable<BottomNavRoute.Theme> {
            Text("Theme")
        }
    }
}
