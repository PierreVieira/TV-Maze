package org.pierre.tvmaze.model.common

import org.pierre.tvmaze.model.data_status.DataStatus

data class ShowItemModel(
    val id: Long,
    val name: DataStatus<String>,
    val image: DataStatus<String>?,
    val premieredYear: DataStatus<String>?,
    val endedYear: DataStatus<String>?,
    val stars: DataStatus<StarsModel>?,
    val isFavorite: DataStatus<Boolean>,
)
