package org.pierre.tvmaze.feature.episodes.domain.usecase

import org.pierre.tvmaze.model.common.episode.EpisodeModel

fun interface GetEpisodes {
    suspend operator fun invoke(mediaId: Long): Result<List<EpisodeModel>>
}
