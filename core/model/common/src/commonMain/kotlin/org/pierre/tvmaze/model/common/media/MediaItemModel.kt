package org.pierre.tvmaze.model.common.media

import org.pierre.tvmaze.model.common.image.ImagesModel
import org.pierre.tvmaze.model.data_status.DataStatus

data class MediaItemModel(
    val id: DataStatus<Long>,
    val name: DataStatus<String>,
    val images: DataStatus<ImagesModel>?,
    val dates: DataStatus<MediaItemDatesModel>?,
    val stars: DataStatus<StarsModel>?,
    val isFavorite: DataStatus<Boolean>,
    val averageRanting: DataStatus<Double>?,
    val summary: DataStatus<String>?,
    val genres: DataStatus<List<String>>?,
)
