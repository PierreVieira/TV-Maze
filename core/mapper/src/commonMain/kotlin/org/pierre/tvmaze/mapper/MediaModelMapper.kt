package org.pierre.tvmaze.mapper

import org.pierre.tvmaze.dto.image.ImageDto
import org.pierre.tvmaze.model.common.image.ImagesModel

fun interface MediaModelMapper {
    fun map(dto: ImageDto): ImagesModel
}
