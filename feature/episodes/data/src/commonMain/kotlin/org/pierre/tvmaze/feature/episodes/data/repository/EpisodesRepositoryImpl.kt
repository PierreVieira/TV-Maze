package org.pierre.tvmaze.feature.episodes.data.repository

import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.pierre.tvmaze.core.room_provider.dao.WatchedEpisodesDao
import org.pierre.tvmaze.dto.episode.EpisodeDto
import org.pierre.tvmaze.feature.episodes.data.mapper.EpisodeWatchedEntityMapper
import org.pierre.tvmaze.feature.episodes.data.mapper.EpisodeWatchedModelMapper
import org.pierre.tvmaze.feature.episodes.data.mapper.WatchedEpisodesIdsMapper
import org.pierre.tvmaze.feature.episodes.domain.repository.EpisodesRepository
import org.pierre.tvmaze.mapper.EpisodeMapper
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.network.data.handler.RequestHandler

internal class EpisodesRepositoryImpl(
    private val requestHandler: RequestHandler,
    private val episodeMapper: EpisodeMapper,
    private val watchedEpisodesDao: WatchedEpisodesDao,
    private val episodeWatchedModelMapper: EpisodeWatchedModelMapper,
    private val episodeWatchedEntityMapper: EpisodeWatchedEntityMapper,
    private val watchedEpisodesIdsMapper: WatchedEpisodesIdsMapper
) : EpisodesRepository {
    override suspend fun getEpisodes(mediaId: Long): Result<List<EpisodeModel>> =
        requestHandler.call<List<EpisodeDto>> {
            get("/shows/$mediaId/episodes")
        }.map { episodes: List<EpisodeDto> ->
            episodes.toDomain(
                mediaId = mediaId,
                watchedIds = watchedEpisodesIdsMapper.map(watchedEpisodesDao.getByMediaId(mediaId)),
            )
        }

    private fun List<EpisodeDto>.toDomain(
        mediaId: Long,
        watchedIds: Set<Long>,
    ): List<EpisodeModel> = mapNotNull {
        episodeMapper.map(
            dto = it,
            mediaId = mediaId,
            watchedIds = watchedIds,
        )
    }

    override fun getWatchedEpisodesFlow(mediaId: Long): Flow<List<EpisodeModel>> =
        watchedEpisodesDao.getByMediaIdAsFlow(mediaId).map { episodes ->
            episodes.map(episodeWatchedModelMapper::map)
        }

    override suspend fun upsertWatchedEpisode(episode: EpisodeModel) {
        val entity = episodeWatchedEntityMapper.mapOrNull(episode) ?: return
        watchedEpisodesDao.upsert(entity)
    }

    override suspend fun deleteWatchedEpisodeById(id: Long) {
        watchedEpisodesDao.deleteById(id)
    }
}
