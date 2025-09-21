package org.pierre.tvmaze.mapper.impl

import org.pierre.tvmaze.dto.media.MediaResultDto
import org.pierre.tvmaze.mapper.MediaItemModelMapper
import org.pierre.tvmaze.mapper.MediaResultMapper
import org.pierre.tvmaze.model.common.media.MediaItemModel

internal class MediaResultMapperImpl(
    private val mediaItemModelMapper: MediaItemModelMapper
) : MediaResultMapper {

    override fun map(dto: MediaResultDto): MediaItemModel? = mediaItemModelMapper.map(dto.media)
}
