package org.pierre.tvmaze.feature.episodes.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.feature.episodes.domain.repository.EpisodesRepository
import org.pierre.tvmaze.feature.episodes.domain.usecase.GetWatchedEpisodesFlow
import org.pierre.tvmaze.model.common.episode.EpisodeModel

class GetWatchedEpisodesFlowUseCase(
    private val repository: EpisodesRepository,
) : GetWatchedEpisodesFlow {
    override fun invoke(mediaId: Long): Flow<List<EpisodeModel>> =
        repository.getWatchedEpisodesFlow(mediaId)
}
