package org.pierre.tvmaze.feature.episodes.data.mapper

import org.pierre.tvmaze.core.room_provider.entity.WatchedEpisodeEntity

interface WatchedEpisodesIdsMapper {
    fun map(entities: List<WatchedEpisodeEntity>): Set<Long>
}
