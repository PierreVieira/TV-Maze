package org.pierre.tvmaze.mapper

import org.pierre.tvmaze.dto.media.MediaDto
import org.pierre.tvmaze.model.common.media.MediaItemModel

fun interface MediaItemModelMapper {
    fun map(dto: MediaDto): MediaItemModel?
}
