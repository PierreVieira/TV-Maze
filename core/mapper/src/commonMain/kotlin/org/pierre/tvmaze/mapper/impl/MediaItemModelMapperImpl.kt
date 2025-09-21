package org.pierre.tvmaze.mapper.impl

import org.pierre.tvmaze.dto.MediaDto
import org.pierre.tvmaze.mapper.MediaItemDatesMapper
import org.pierre.tvmaze.mapper.MediaItemModelMapper
import org.pierre.tvmaze.mapper.StarsMapper
import org.pierre.tvmaze.model.common.MediaItemModel
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class MediaItemModelMapperImpl(
    private val starsMapper: StarsMapper,
    private val datesMapper: MediaItemDatesMapper,
    private val htmlTextCleaner: org.pierre.tvmaze.mapper.HtmlTextCleaner,
): MediaItemModelMapper {
    override fun map(dto: MediaDto): MediaItemModel? = dto.run {
        MediaItemModel(
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
            )?.toLoadedStatus(),
            summary = summary?.let(htmlTextCleaner::clean)?.toLoadedStatus(),
            genres = genres?.let { DataStatus.Loaded(it) },
        )
    }
}
