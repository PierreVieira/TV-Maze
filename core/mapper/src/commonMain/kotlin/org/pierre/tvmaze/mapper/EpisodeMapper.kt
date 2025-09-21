package org.pierre.tvmaze.mapper

import org.pierre.tvmaze.dto.episode.EpisodeDto
import org.pierre.tvmaze.model.common.episode.EpisodeModel

fun interface EpisodeMapper {
    fun map(
        dto: EpisodeDto,
        mediaId: Long,
        watchedIds: Set<Long>,
    ): EpisodeModel?
}
