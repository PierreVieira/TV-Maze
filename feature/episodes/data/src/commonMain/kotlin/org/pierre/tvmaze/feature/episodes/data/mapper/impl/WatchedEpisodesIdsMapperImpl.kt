package org.pierre.tvmaze.feature.episodes.data.mapper.impl

import org.pierre.tvmaze.core.room_provider.entity.WatchedEpisodeEntity
import org.pierre.tvmaze.feature.episodes.data.mapper.WatchedEpisodesIdsMapper

class WatchedEpisodesIdsMapperImpl: WatchedEpisodesIdsMapper {
    override fun map(entities: List<WatchedEpisodeEntity>): Set<Long> = entities.map { it.id }.toSet()
}
