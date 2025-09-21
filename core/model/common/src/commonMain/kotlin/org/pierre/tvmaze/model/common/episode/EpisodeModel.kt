package org.pierre.tvmaze.model.common.episode

import org.pierre.tvmaze.model.common.image.ImagesModel
import org.pierre.tvmaze.model.data_status.DataStatus

data class EpisodeModel(
    val id: DataStatus<Long>,
    val mediaId: Long,
    val name: DataStatus<String>?,
    val season: DataStatus<Int>?,
    val number: DataStatus<Int>?,
    val image: DataStatus<ImagesModel>?,
    val isWatched: DataStatus<Boolean>
)
