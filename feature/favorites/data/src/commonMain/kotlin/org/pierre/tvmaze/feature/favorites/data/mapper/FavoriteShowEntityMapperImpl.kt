package org.pierre.tvmaze.feature.favorites.data.mapper

import org.pierre.tvmaze.core.room_provider.entity.FavoriteMediaEntity
import org.pierre.tvmaze.model.common.media.MediaItemDatesModel
import org.pierre.tvmaze.model.common.media.MediaItemModel
import org.pierre.tvmaze.model.data_status.toLoadedData

class FavoriteShowEntityMapperImpl : FavoriteShowEntityMapper {
    override fun mapOrNull(model: MediaItemModel): FavoriteMediaEntity? = with(model) {
        val id = id.toLoadedData() ?: return null
        val name = name.toLoadedData() ?: return null
        val imagesUrl = images?.toLoadedData()
        val datesLoaded = dates?.toLoadedData()
        val (startYear, endYear) = when (datesLoaded) {
            is MediaItemDatesModel.Running -> datesLoaded.startYear to null
            is MediaItemDatesModel.StartAndEnd -> datesLoaded.startYear to datesLoaded.endYear
            null -> null to null
        }
        val averageRating = averageRanting?.toLoadedData()
        val starsLoaded = stars?.toLoadedData()
        val fullStarsAmount = starsLoaded?.fullStarsAmount
        val showInAHalf = starsLoaded?.showInAHalf
        return FavoriteMediaEntity(
            id = id,
            name = name,
            originalImageUrl = imagesUrl?.original,
            mediumImageUrl = imagesUrl?.medium,
            startYear = startYear,
            endYear = endYear,
            averageRating = averageRating,
            fullStarsAmount = fullStarsAmount,
            showStarInAHalf = showInAHalf,
        )
    }
}
