package org.pierre.tvmaze.feature.episodes.domain.usecase.impl

import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.feature.episodes.domain.repository.EpisodesRepository
import org.pierre.tvmaze.feature.episodes.domain.usecase.EpisodesToSeason
import org.pierre.tvmaze.feature.episodes.domain.usecase.GetEpisodesBySeason

class GetEpisodesBySeasonUseCase(
    private val repository: EpisodesRepository,
    private val episodesToSeason: EpisodesToSeason,
): GetEpisodesBySeason {

    override suspend fun invoke(mediaId: Long): Result<List<SeasonModel>> =
        repository.getEpisodes(mediaId).map(episodesToSeason::map)
}
