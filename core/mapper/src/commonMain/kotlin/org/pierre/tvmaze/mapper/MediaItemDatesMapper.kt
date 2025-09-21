package org.pierre.tvmaze.mapper

import org.pierre.tvmaze.model.common.media.MediaItemDatesModel

interface MediaItemDatesMapper {
    fun map(
        status: String?,
        premiered: String?,
        ended: String?,
    ): MediaItemDatesModel?
}
