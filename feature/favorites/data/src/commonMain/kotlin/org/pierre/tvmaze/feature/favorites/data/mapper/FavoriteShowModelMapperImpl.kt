package org.pierre.tvmaze.feature.favorites.data.mapper

import org.pierre.tvmaze.core.room_provider.entity.FavoriteShowEntity
import org.pierre.tvmaze.model.common.ShowItemDatesModel
import org.pierre.tvmaze.model.common.ShowItemModel
import org.pierre.tvmaze.model.common.StarsModel
import org.pierre.tvmaze.model.data_status.DataStatus

class FavoriteShowModelMapperImpl : FavoriteShowModelMapper {
    override fun map(entity: FavoriteShowEntity): ShowItemModel = with(entity) {
        val start = startYear
        val end = endYear
        val dates: ShowItemDatesModel? = when {
            start != null && end == null -> ShowItemDatesModel.Running(start)
            start != null && end != null -> ShowItemDatesModel.StartAndEnd(start, end)
            else -> null
        }
        val fullStars = fullStarsAmount
        val half = showStarInAHalf
        val starsModel: StarsModel? = if (fullStars != null && half != null) {
            StarsModel(fullStars, half)
        } else null
        return ShowItemModel(
            id = DataStatus.Loaded(id),
            name = DataStatus.Loaded(name),
            image = imageUrl?.let { DataStatus.Loaded(it) },
            dates = dates?.let { DataStatus.Loaded(it) },
            stars = starsModel?.let { DataStatus.Loaded(it) },
            isFavorite = DataStatus.Loaded(true),
            averageRanting = averageRating?.let { DataStatus.Loaded(it) },
        )
    }
}
