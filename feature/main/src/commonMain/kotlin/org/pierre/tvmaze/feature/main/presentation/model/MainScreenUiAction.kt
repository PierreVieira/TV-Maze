package org.pierre.tvmaze.feature.main.presentation.model

sealed interface MainScreenUiAction {
    data class NavigateToBottomRoute(val route: Any) : MainScreenUiAction
}
