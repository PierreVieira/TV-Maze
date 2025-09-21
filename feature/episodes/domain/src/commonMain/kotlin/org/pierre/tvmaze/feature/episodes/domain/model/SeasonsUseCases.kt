package org.pierre.tvmaze.feature.episodes.domain.model

import org.pierre.tvmaze.feature.episodes.domain.usecase.GetEpisodesBySeason
import org.pierre.tvmaze.feature.episodes.domain.usecase.GetWatchedEpisodesBySeasonFlow
import org.pierre.tvmaze.feature.episodes.domain.usecase.ToggleEpisodeWatched
import org.pierre.tvmaze.feature.episodes.domain.usecase.UpdateSeasonsWithWatched

data class SeasonsUseCases(
    val getEpisodesBySeason: GetEpisodesBySeason,
    val toggleEpisodeWatched: ToggleEpisodeWatched,
    val updateSeasonsWithWatched: UpdateSeasonsWithWatched,
    val getWatchedEpisodesBySeasonFlow: GetWatchedEpisodesBySeasonFlow,
)
