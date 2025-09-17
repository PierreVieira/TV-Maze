package org.pierre.tvmaze.feature.main.presentation.model

sealed interface MainScreenUiEvent {
    data class BottomNavItemClicked(val route: Any): MainScreenUiEvent
}
