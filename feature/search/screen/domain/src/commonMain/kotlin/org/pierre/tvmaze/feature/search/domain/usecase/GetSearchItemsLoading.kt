package org.pierre.tvmaze.feature.search.domain.usecase

import org.pierre.tvmaze.model.common.MediaItemCard

interface GetSearchItemsLoading {
    operator fun invoke(): List<MediaItemCard>
}
