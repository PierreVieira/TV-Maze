package org.pierre.tvmaze.mapper

import org.pierre.tvmaze.model.common.ShowItemDatesModel

interface ShowItemDatesMapper {
    fun map(
        status: String?,
        premiered: String?,
        ended: String?,
    ): ShowItemDatesModel?
}
