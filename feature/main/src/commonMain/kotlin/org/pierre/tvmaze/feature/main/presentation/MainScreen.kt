package org.pierre.tvmaze.feature.main.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import org.jetbrains.compose.resources.stringResource
import org.pierre.tvmaze.core.utils.orFalse
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavRoute
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavigationItemModel
import org.pierre.tvmaze.feature.main.presentation.model.MainScreenUiEvent

@Composable
fun MainScreen(
    currentDestination: NavDestination?,
    bottomNavigationModels: List<BottomNavigationItemModel<Any>>,
    onEvent: (MainScreenUiEvent) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            bottomNavigationModels.forEach { bottomNavigationItemModel: BottomNavigationItemModel<Any> ->
                val presentationItem = bottomNavigationItemModel.presentationModel
                val isSelected = isSelected(currentDestination, bottomNavigationItemModel)
                item(
                    selected = isSelected,
                    onClick = {
                        onEvent(
                            MainScreenUiEvent.BottomNavItemClicked(
                                bottomNavigationItemModel.route,
                            )
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = presentationItem.icon,
                            contentDescription = stringResource(presentationItem.title)
                        )
                    },
                    label = {
                        Text(
                            stringResource(presentationItem.title),
                            textAlign = TextAlign.Center,
                        )
                    },
                )
            }
        },
        content = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                content = content,
            )
        },
    )
}

private fun isSelected(
    currentDestination: NavDestination?,
    bottomNavigationItemModel: BottomNavigationItemModel<Any>,
): Boolean = currentDestination?.hierarchy
    ?.any { navDestination: NavDestination ->
        (bottomNavigationItemModel.route as? BottomNavRoute)?.let { route ->
            navDestination.hasRoute(route::class)
        }.orFalse()
    }.orFalse()
