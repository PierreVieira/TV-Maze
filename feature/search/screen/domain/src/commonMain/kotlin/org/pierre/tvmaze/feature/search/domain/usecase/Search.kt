package org.pierre.tvmaze.feature.search.domain.usecase

import org.pierre.tvmaze.model.common.media.MediaItemModel

fun interface Search {
    suspend operator fun invoke(query: String): Result<List<MediaItemModel>>
}
