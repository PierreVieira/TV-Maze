package org.pierre.tvmaze.feature.episodes.data.repository

import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.pierre.tvmaze.core.room_provider.dao.WatchedEpisodesDao
import org.pierre.tvmaze.dto.episode.EpisodeDto
import org.pierre.tvmaze.feature.episodes.data.mapper.EpisodeWatchedEntityMapper
import org.pierre.tvmaze.feature.episodes.data.mapper.EpisodeWatchedModelMapper
import org.pierre.tvmaze.feature.episodes.domain.repository.EpisodesRepository
import org.pierre.tvmaze.mapper.EpisodeMapper
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.data_status.toLoadedInformation
import org.pierre.tvmaze.model.data_status.toLoadedStatus
import org.pierre.tvmaze.network.data.handler.RequestHandler

internal class EpisodesRepositoryImpl(
    private val requestHandler: RequestHandler,
    private val episodeMapper: EpisodeMapper,
    private val watchedEpisodesDao: WatchedEpisodesDao,
    private val episodeWatchedModelMapper: EpisodeWatchedModelMapper,
    private val episodeWatchedEntityMapper: EpisodeWatchedEntityMapper,
) : EpisodesRepository {
    override suspend fun getEpisodes(mediaId: Long): Result<List<EpisodeModel>> =
        requestHandler.call<List<EpisodeDto>> {
            get("/shows/$mediaId/episodes")
        }.map { episodes: List<EpisodeDto> ->
            episodes.toDomain(
                mediaId = mediaId,
                watchedIds = watchedEpisodesDao.getByMediaId(mediaId).map { it.id }.toSet(),
            )
        }

    private fun List<EpisodeDto>.toDomain(
        mediaId: Long,
        watchedIds: Set<Long>,
    ): List<EpisodeModel> = mapNotNull {
        episodeMapper.map(
            dto = it,
            mediaId = mediaId,
        )
    }.map { model ->
        val id = model.id.toLoadedInformation()?.data
        val isWatched = id != null && watchedIds.contains(id)
        if (isWatched) {
            model.copy(isWatched = true.toLoadedStatus())
        } else {
            model
        }
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
