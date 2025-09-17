package org.pierre.tvmaze.ui.navigation.domain

import kotlinx.serialization.Serializable

sealed interface NavRoute {
    @Serializable
    data object Main : NavRoute
}
