package org.pierre.tvmaze.mapper.impl

import org.pierre.tvmaze.dto.ShowResultDto
import org.pierre.tvmaze.mapper.ShowItemModelMapper
import org.pierre.tvmaze.mapper.StarsMapper
import org.pierre.tvmaze.model.common.ShowItemModel
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class ShowItemModelMapperImpl(private val starsMapper: StarsMapper) : ShowItemModelMapper {

    override fun map(dto: ShowResultDto): ShowItemModel? = with(dto.show) {
        ShowItemModel(
            id = id ?: return null,
            name = name.orEmpty().toLoadedStatus(),
            image = image?.medium?.toLoadedStatus(),
            premieredYear = premiered?.toLoadedStatus(),
            endedYear = ended?.toLoadedStatus(),
            stars = ranting?.average?.let(starsMapper::map)?.toLoadedStatus(),
            isFavorite = false.toLoadedStatus(),
        )
    }
}
