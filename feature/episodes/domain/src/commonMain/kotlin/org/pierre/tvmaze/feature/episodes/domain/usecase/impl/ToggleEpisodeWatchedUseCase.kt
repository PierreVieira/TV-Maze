package org.pierre.tvmaze.feature.episodes.domain.usecase.impl

import org.pierre.tvmaze.feature.episodes.domain.repository.EpisodesRepository
import org.pierre.tvmaze.feature.episodes.domain.usecase.ToggleEpisodeWatched
import org.pierre.tvmaze.model.data_status.toLoadedInformation
import org.pierre.tvmaze.model.common.episode.EpisodeModel

internal class ToggleEpisodeWatchedUseCase(
    private val repository: EpisodesRepository,
) : ToggleEpisodeWatched {
    override suspend fun invoke(episode: EpisodeModel) {
        val id = episode.id.toLoadedInformation()?.data ?: return
        val isWatched = episode.isWatched.toLoadedInformation()?.data ?: false
        if (isWatched) {
            repository.deleteWatchedEpisodeById(id)
        } else {
            repository.upsertWatchedEpisode(episode)
        }
    }
}
