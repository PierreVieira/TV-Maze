package org.pierre.tvmaze.feature.episodes.domain.usecase.impl

import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.feature.episodes.domain.usecase.EpisodesToSeason
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.tvmaze.model.data_status.toLoadedInformation

class EpisodesToSeasonsUseCase : EpisodesToSeason {
    override fun map(episodes: List<EpisodeModel>): List<SeasonModel> {
        if (episodes.isEmpty()) return emptyList()

        val bySeason = mutableMapOf<Int, MutableList<EpisodeModel>>()
        for (ep in episodes) {
            val seasonNumber = ep.season?.toLoadedInformation()?.data ?: continue
            bySeason.getOrPut(seasonNumber) { mutableListOf() }.add(ep)
        }

        val seasonNumbers = bySeason.keys.toList().sorted()
        return seasonNumbers.map { seasonNumber ->
            val seasonEpisodes = bySeason[seasonNumber].orEmpty()
                .sortedBy { it.number?.toLoadedInformation()?.data ?: Int.MAX_VALUE }
            val mediaId = seasonEpisodes.firstOrNull()?.mediaId ?: 0L
            SeasonModel(
                mediaId = mediaId,
                number = DataStatus.Loaded(seasonNumber),
                episodes = seasonEpisodes,
            )
        }
    }
}
