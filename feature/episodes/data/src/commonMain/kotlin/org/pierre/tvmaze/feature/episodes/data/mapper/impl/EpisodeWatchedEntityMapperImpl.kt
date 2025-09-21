package org.pierre.tvmaze.feature.episodes.data.mapper.impl

import org.pierre.tvmaze.core.room_provider.entity.WatchedEpisodeEntity
import org.pierre.tvmaze.feature.episodes.data.mapper.EpisodeWatchedEntityMapper
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.data_status.toLoadedInformation

internal class EpisodeWatchedEntityMapperImpl : EpisodeWatchedEntityMapper {
    override fun mapOrNull(model: EpisodeModel): WatchedEpisodeEntity? = with(model) {
        val idLoaded = id.toLoadedInformation()?.data ?: return null
        WatchedEpisodeEntity(
            id = idLoaded,
            mediaId = mediaId,
            name = name?.toLoadedInformation()?.data,
            season = season?.toLoadedInformation()?.data,
            number = number?.toLoadedInformation()?.data,
            originalImageUrl = image?.toLoadedInformation()?.data?.original,
            mediumImageUrl = image?.toLoadedInformation()?.data?.medium,
        )
    }
}
