package org.pierre.tvmaze.mapper.impl

import org.pierre.tvmaze.dto.image.ImageDto
import org.pierre.tvmaze.mapper.MediaModelMapper
import org.pierre.tvmaze.model.common.image.ImagesModel

class MediaModelMapperImpl: MediaModelMapper {
    override fun map(dto: ImageDto): ImagesModel = dto.run {
        ImagesModel(
            medium = medium,
            original = original
        )
    }
}
