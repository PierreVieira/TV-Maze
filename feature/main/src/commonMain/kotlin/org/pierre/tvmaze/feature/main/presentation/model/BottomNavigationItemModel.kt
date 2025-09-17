package org.pierre.tvmaze.feature.main.presentation.model

data class BottomNavigationItemModel<T: Any>(
    val presentationModel: BottomNavigationItemPresentationModel,
    val route: T,
)
