package org.pierre.tvmaze.feature.favorites.data.mapper

import org.pierre.tvmaze.core.room_provider.entity.FavoriteMediaEntity
import org.pierre.tvmaze.model.common.image.ImagesModel
import org.pierre.tvmaze.model.common.media.MediaItemDatesModel
import org.pierre.tvmaze.model.common.media.MediaItemModel
import org.pierre.tvmaze.model.common.media.StarsModel
import org.pierre.tvmaze.model.data_status.DataStatus

class FavoriteMediaModelMapperImpl : FavoriteMediaModelMapper {
    override fun map(entity: FavoriteMediaEntity): MediaItemModel = with(entity) {
        val start = startYear
        val end = endYear
        val dates: MediaItemDatesModel? = when {
            start != null && end == null -> MediaItemDatesModel.Running(start)
            start != null && end != null -> MediaItemDatesModel.StartAndEnd(start, end)
            else -> null
        }
        val fullStars = fullStarsAmount
        val half = showStarInAHalf
        val starsModel: StarsModel? = if (fullStars != null && half != null) {
            StarsModel(fullStars, half)
        } else null
        return MediaItemModel(
            id = DataStatus.Loaded(id),
            name = DataStatus.Loaded(name),
            images = DataStatus.Loaded(
                data = ImagesModel(
                    medium = mediumImageUrl,
                    original = originalImageUrl,
                ),
            ),
            dates = dates?.let { DataStatus.Loaded(it) },
            stars = starsModel?.let { DataStatus.Loaded(it) },
            isFavorite = DataStatus.Loaded(true),
            averageRanting = averageRating?.let { DataStatus.Loaded(it) },
            summary = null,
            genres = null,
        )
    }
}
