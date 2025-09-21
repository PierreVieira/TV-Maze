package org.pierre.tvmaze.mapper

import org.pierre.tvmaze.dto.MediaResultDto
import org.pierre.tvmaze.model.common.MediaItemModel

interface MediaResultMapper {

    fun map(dto: MediaResultDto): MediaItemModel?
}
