package org.pierre.tvmaze.feature.episodes.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.model.common.episode.EpisodeModel

interface GetWatchedEpisodesFlow {
    operator fun invoke(mediaId: Long): Flow<List<EpisodeModel>>
}
