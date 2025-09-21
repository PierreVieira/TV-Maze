package org.pierre.tvmaze.mapper

import org.pierre.tvmaze.dto.media.MediaResultDto
import org.pierre.tvmaze.model.common.media.MediaItemModel

fun interface MediaResultMapper {

    fun map(dto: MediaResultDto): MediaItemModel?
}
