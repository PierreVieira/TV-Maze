package org.pierre.tvmaze.feature.episodes.domain.usecase

import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel

fun interface GetEpisodesBySeason {
    suspend operator fun invoke(mediaId: Long): Result<List<SeasonModel>>
}
