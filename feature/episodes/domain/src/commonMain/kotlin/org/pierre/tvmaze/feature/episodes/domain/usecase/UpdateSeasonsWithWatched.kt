package org.pierre.tvmaze.feature.episodes.domain.usecase

import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel

fun interface UpdateSeasonsWithWatched {
    operator fun invoke(
        watchedSeasons: List<SeasonModel>,
        currentSeasons: List<SeasonModel>,
    ): List<SeasonModel>
}
