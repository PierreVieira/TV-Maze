package org.pierre.tvmaze.feature.episodes.domain.usecase.impl

import org.pierre.tvmaze.feature.episodes.domain.repository.EpisodesRepository
import org.pierre.tvmaze.feature.episodes.domain.usecase.GetEpisodes
import org.pierre.tvmaze.model.common.episode.EpisodeModel

class GetEpisodesUseCase(
    private val repository: EpisodesRepository
): GetEpisodes {

    override suspend fun invoke(mediaId: Long): Result<List<EpisodeModel>> =
        repository.getEpisodes(mediaId)
}
