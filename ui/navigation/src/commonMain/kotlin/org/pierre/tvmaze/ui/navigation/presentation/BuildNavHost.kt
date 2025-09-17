package org.pierre.tvmaze.ui.navigation.presentation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import org.pierre.tvmaze.ui.navigation.presentation.graph.mainScreen

fun NavGraphBuilder.buildNavHost(
    getNavigationModifier: (onBack: () -> Unit) -> Modifier,
) {
    mainScreen(
        getNavigationModifier = getNavigationModifier,
    )
}
