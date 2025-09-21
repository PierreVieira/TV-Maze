package org.pierre.tvmaze.feature.episodes.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel

fun interface GetWatchedEpisodesBySeasonFlow {
    operator fun invoke(mediaId: Long): Flow<List<SeasonModel>>
}
