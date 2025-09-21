package org.pierre.tvmaze.mapper.impl

import org.pierre.tvmaze.dto.ShowResultDto
import org.pierre.tvmaze.mapper.ShowItemDatesMapper
import org.pierre.tvmaze.mapper.ShowItemModelMapper
import org.pierre.tvmaze.mapper.StarsMapper
import org.pierre.tvmaze.model.common.ShowItemModel
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class ShowItemModelMapperImpl(
    private val starsMapper: StarsMapper,
    private val datesMapper: ShowItemDatesMapper,
) : ShowItemModelMapper {

    override fun map(dto: ShowResultDto): ShowItemModel? = with(dto.show) {
        ShowItemModel(
            id = id?.toLoadedStatus() ?: return null,
            name = name.orEmpty().toLoadedStatus(),
            image = image?.medium?.toLoadedStatus(),
            stars = rating?.average?.let(starsMapper::map)?.toLoadedStatus(),
            isFavorite = false.toLoadedStatus(),
            averageRanting = rating?.average?.toLoadedStatus(),
            dates = datesMapper.map(
                status = status,
                premiered = premiered,
                ended = ended
            )?.toLoadedStatus()
        )
    }
}
