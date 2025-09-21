package org.pierre.tvmaze.feature.episodes.domain.usecase

import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.model.common.episode.EpisodeModel

interface EpisodesToSeason {
    fun map(episodes: List<EpisodeModel>): List<SeasonModel>
}
