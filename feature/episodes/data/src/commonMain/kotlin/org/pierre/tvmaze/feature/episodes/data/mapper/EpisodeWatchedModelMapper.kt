package org.pierre.tvmaze.feature.episodes.data.mapper

import org.pierre.tvmaze.core.room_provider.entity.WatchedEpisodeEntity
import org.pierre.tvmaze.model.common.episode.EpisodeModel

interface EpisodeWatchedModelMapper {
    fun map(entity: WatchedEpisodeEntity): EpisodeModel
}
