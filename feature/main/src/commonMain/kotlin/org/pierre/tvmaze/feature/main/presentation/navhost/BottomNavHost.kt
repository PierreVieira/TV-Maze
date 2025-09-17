package org.pierre.tvmaze.feature.main.presentation.navhost

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.tvmaze.feature.favorites.presentation.FavoritesScreen
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavRoute
import org.pierre.tvmaze.feature.search.presentation.SearchScreen
import org.pierre.tvmaze.feature.theme_selection.presentation.ThemeSelectionScreen
import org.pierre.tvmaze.feature.theme_selection.presentation.ThemeSelectionViewModel

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
            SearchScreen()
        }
        composable<BottomNavRoute.Favorites> {
            FavoritesScreen()
        }
        composable<BottomNavRoute.Theme> {
            val themeSelectionViewModel = koinViewModel<ThemeSelectionViewModel>()
            ThemeSelectionScreen(
                options = themeSelectionViewModel.themeOptions,
                setTheme = themeSelectionViewModel::setTheme
            )
        }
    }
}
