package org.pierre.tvmaze.mapper

import org.pierre.tvmaze.dto.ShowResultDto
import org.pierre.tvmaze.model.common.ShowItemModel

interface ShowItemModelMapper {

    fun map(dto: ShowResultDto): ShowItemModel?
}
