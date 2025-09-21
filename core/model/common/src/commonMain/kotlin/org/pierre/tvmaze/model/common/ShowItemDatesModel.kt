package org.pierre.tvmaze.model.common

sealed interface ShowItemDatesModel {
    val startYear: Int
    data class Running(override val startYear: Int): ShowItemDatesModel
    data class StartAndEnd(override val startYear: Int, val endYear: Int): ShowItemDatesModel
}
