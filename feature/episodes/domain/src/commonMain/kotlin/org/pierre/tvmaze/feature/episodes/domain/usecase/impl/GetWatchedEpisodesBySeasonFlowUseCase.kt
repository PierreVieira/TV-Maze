package org.pierre.tvmaze.feature.episodes.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.feature.episodes.domain.repository.EpisodesRepository
import org.pierre.tvmaze.feature.episodes.domain.usecase.EpisodesToSeason
import org.pierre.tvmaze.feature.episodes.domain.usecase.GetWatchedEpisodesBySeasonFlow

class GetWatchedEpisodesBySeasonFlowUseCase(
    private val repository: EpisodesRepository,
    private val episodesToSeason: EpisodesToSeason,
) : GetWatchedEpisodesBySeasonFlow {
    override fun invoke(mediaId: Long): Flow<List<SeasonModel>> =
        repository.getWatchedEpisodesFlow(mediaId).map(episodesToSeason::map)
}
