package org.pierre.tvmaze.feature.episodes.domain.usecase.impl

import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.feature.episodes.domain.usecase.UpdateSeasonsWithWatched
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.model.data_status.toLoadedStatus

internal class UpdateSeasonsWithWatchedUseCase : UpdateSeasonsWithWatched {
    override fun invoke(
        watchedSeasons: List<SeasonModel>,
        currentSeasons: List<SeasonModel>,
    ): List<SeasonModel> {
        val watchedIds: Set<Long> = watchedSeasons
            .flatMap { it.episodes }
            .mapNotNull { it.id.toLoadedData() }
            .toSet()

        return currentSeasons.map { season ->
            season.copy(
                episodes = season.episodes.map { episode ->
                    val isWatched = watchedIds.contains(episode.id.toLoadedData())
                    episode.copy(isWatched = isWatched.toLoadedStatus())
                }
            )
        }
    }
}
