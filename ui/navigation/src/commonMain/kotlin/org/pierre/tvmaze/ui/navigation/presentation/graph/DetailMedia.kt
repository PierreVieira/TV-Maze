package org.pierre.tvmaze.ui.navigation.presentation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.pierre.tvmaze.feature.media_details.presentation.MediaDetailsScreen
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsRoute

internal fun NavGraphBuilder.detailMedia(navHostController: NavHostController) {
    composable<MediaDetailsRoute> { backStackEntry ->
        val route: MediaDetailsRoute = backStackEntry.toRoute()
        MediaDetailsScreen(
            id = route.id,
            onBack = navHostController::navigateUp,
        )
    }
}
