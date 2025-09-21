package org.pierre.tvmaze.feature.episodes.data.mapper.impl

import org.pierre.tvmaze.core.room_provider.entity.WatchedEpisodeEntity
import org.pierre.tvmaze.feature.episodes.data.mapper.EpisodeWatchedModelMapper
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.common.image.ImagesModel
import org.pierre.tvmaze.model.data_status.toLoadedStatus

internal class EpisodeWatchedModelMapperImpl : EpisodeWatchedModelMapper {
    override fun map(entity: WatchedEpisodeEntity): EpisodeModel = with(entity) {
        EpisodeModel(
            id = id.toLoadedStatus(),
            mediaId = mediaId,
            name = name?.toLoadedStatus(),
            season = season?.toLoadedStatus(),
            number = number?.toLoadedStatus(),
            image = ImagesModel(
                medium = mediumImageUrl,
                original = originalImageUrl,
            ).toLoadedStatus(),
            isWatched = true.toLoadedStatus()
        )
    }
}
