package org.pierre.tvmaze.feature.main.presentation.bottom_nav_route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.pierre.tvmaze.feature.favorites.presentation.FavoritesScreen
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavRoute

fun NavGraphBuilder.favorites() {
    composable<BottomNavRoute.Favorites> {
        FavoritesScreen()
    }
}
