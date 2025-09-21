package org.pierre.tvmaze.feature.episodes.domain.usecase

import org.pierre.tvmaze.model.common.episode.EpisodeModel

fun interface ToggleEpisodeWatched {
    suspend operator fun invoke(episode: EpisodeModel)
}
