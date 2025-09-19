package org.pierre.tvmaze

import kotlinx.serialization.Serializable

sealed interface AndroidNavRoute {

    @Serializable
    data object MaterialYouBottomSheet : AndroidNavRoute
}
