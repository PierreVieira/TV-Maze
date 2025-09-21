package org.pierre.tvmaze.feature.search.domain.usecase

import org.pierre.tvmaze.model.common.MediaItemCard

fun interface Search {
    suspend operator fun invoke(query: String): Result<List<MediaItemCard>>
}
