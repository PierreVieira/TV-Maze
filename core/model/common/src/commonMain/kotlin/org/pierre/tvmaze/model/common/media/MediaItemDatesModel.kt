package org.pierre.tvmaze.model.common.media

sealed interface MediaItemDatesModel {
    val startYear: Int
    data class Running(override val startYear: Int): MediaItemDatesModel
    data class StartAndEnd(override val startYear: Int, val endYear: Int): MediaItemDatesModel
}
