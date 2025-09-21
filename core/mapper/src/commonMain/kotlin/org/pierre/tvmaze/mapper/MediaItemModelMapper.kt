package org.pierre.tvmaze.mapper

import org.pierre.tvmaze.dto.MediaResultDto
import org.pierre.tvmaze.model.common.MediaItemCard

interface MediaItemModelMapper {

    fun map(dto: MediaResultDto): MediaItemCard?
}
