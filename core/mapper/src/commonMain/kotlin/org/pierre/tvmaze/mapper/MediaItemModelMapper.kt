package org.pierre.tvmaze.mapper

import org.pierre.tvmaze.dto.MediaDto
import org.pierre.tvmaze.model.common.MediaItemModel

fun interface MediaItemModelMapper {
    fun map(dto: MediaDto): MediaItemModel?
}
