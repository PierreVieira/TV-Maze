package org.pierre.tvmaze.feature.favorites.data.mapper

import org.pierre.tvmaze.core.room_provider.entity.FavoriteShowEntity
import org.pierre.tvmaze.model.common.ShowItemDatesModel
import org.pierre.tvmaze.model.common.ShowItemModel
import org.pierre.tvmaze.model.data_status.toLoadedData

class FavoriteShowEntityMapperImpl : FavoriteShowEntityMapper {
    override fun mapOrNull(model: ShowItemModel): FavoriteShowEntity? = with(model) {
        val id = id.toLoadedData() ?: return null
        val name = name.toLoadedData() ?: return null
        val imageUrl = image?.toLoadedData()
        val datesLoaded = dates?.toLoadedData()
        val (startYear, endYear) = when (datesLoaded) {
            is ShowItemDatesModel.Running -> datesLoaded.startYear to null
            is ShowItemDatesModel.StartAndEnd -> datesLoaded.startYear to datesLoaded.endYear
            null -> null to null
        }
        val averageRating = averageRanting?.toLoadedData()
        val starsLoaded = stars?.toLoadedData()
        val fullStarsAmount = starsLoaded?.fullStarsAmount
        val showInAHalf = starsLoaded?.showInAHalf
        return FavoriteShowEntity(
            id = id,
            name = name,
            imageUrl = imageUrl,
            startYear = startYear,
            endYear = endYear,
            averageRating = averageRating,
            fullStarsAmount = fullStarsAmount,
            showStarInAHalf = showInAHalf,
        )
    }
}
