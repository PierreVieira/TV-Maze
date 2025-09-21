package org.pierre.tvmaze.feature.episodes.domain.repository

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.model.common.episode.EpisodeModel

interface EpisodesRepository {
    suspend fun getEpisodes(mediaId: Long): Result<List<EpisodeModel>>
    fun getWatchedEpisodesFlow(mediaId: Long): Flow<List<EpisodeModel>>
}
