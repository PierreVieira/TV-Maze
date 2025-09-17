package org.pierre.tvmaze.feature.main.presentation.model

import kotlinx.serialization.Serializable

sealed interface BottomNavRoute {

    @Serializable
    data object Search : BottomNavRoute

    @Serializable
    data object Favorites : BottomNavRoute

    @Serializable
    data object Theme : BottomNavRoute
}
