package org.pierre.tvmaze.mapper.impl

import org.pierre.tvmaze.dto.episode.EpisodeDto
import org.pierre.tvmaze.mapper.EpisodeMapper
import org.pierre.tvmaze.mapper.MediaModelMapper
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.data_status.toLoadedStatus

internal class EpisodeMapperImpl(
    private val mediaModelMapper: MediaModelMapper,
) : EpisodeMapper {
    override fun map(
        dto: EpisodeDto,
        mediaId: Long,
    ): EpisodeModel? = dto.run {
        EpisodeModel(
            id = id?.toLoadedStatus() ?: return null,
            name = name?.toLoadedStatus(),
            season = season?.toLoadedStatus(),
            number = number?.toLoadedStatus(),
            mediaId = mediaId,
            image = image?.let(mediaModelMapper::map)?.toLoadedStatus(),
            isWatched = false.toLoadedStatus()
        )
    }
}
