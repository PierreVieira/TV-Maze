package org.pierre.tvmaze.feature.episodes.domain.model

import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.data_status.DataStatus

data class SeasonModel(
    val mediaId: Long,
    val number: DataStatus<Int>,
    val episodes: List<EpisodeModel>
)
