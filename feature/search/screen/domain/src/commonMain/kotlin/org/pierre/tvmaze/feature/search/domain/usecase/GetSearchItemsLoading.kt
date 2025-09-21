package org.pierre.tvmaze.feature.search.domain.usecase

import org.pierre.tvmaze.model.common.media.MediaItemModel

interface GetSearchItemsLoading {
    operator fun invoke(): List<MediaItemModel>
}
