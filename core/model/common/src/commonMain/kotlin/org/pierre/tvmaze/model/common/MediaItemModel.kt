package org.pierre.tvmaze.model.common

import org.pierre.tvmaze.model.data_status.DataStatus

data class MediaItemModel(
    val id: DataStatus<Long>,
    val name: DataStatus<String>,
    val image: DataStatus<String>?,
    val dates: DataStatus<MediaItemDatesModel>?,
    val stars: DataStatus<StarsModel>?,
    val isFavorite: DataStatus<Boolean>,
    val averageRanting: DataStatus<Double>?,
)
