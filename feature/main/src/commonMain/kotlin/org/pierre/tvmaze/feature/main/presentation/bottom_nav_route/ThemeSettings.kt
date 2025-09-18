package org.pierre.tvmaze.feature.main.presentation.bottom_nav_route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavRoute
import org.pierre.tvmaze.feature.theme_selection.presentation.ThemeSelectionScreen
import org.pierre.tvmaze.feature.theme_selection.presentation.ThemeSelectionViewModel

fun NavGraphBuilder.themeSettings() {
    composable<BottomNavRoute.Theme> {
        val viewModel = koinViewModel<ThemeSelectionViewModel>()
        ThemeSelectionScreen(
            options = viewModel.themeOptions,
            setTheme = viewModel::setTheme
        )
    }
}
