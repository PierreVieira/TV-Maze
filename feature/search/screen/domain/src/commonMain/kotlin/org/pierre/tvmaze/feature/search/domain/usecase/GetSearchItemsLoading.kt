package org.pierre.tvmaze.feature.search.domain.usecase

import org.pierre.tvmaze.model.common.MediaItemModel

interface GetSearchItemsLoading {
    operator fun invoke(): List<MediaItemModel>
}
