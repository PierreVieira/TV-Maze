package org.pierre.tvmaze.feature.search.domain.usecase

import org.pierre.tvmaze.model.common.ShowItemModel

interface GetSearchItemsLoading {
    operator fun invoke(): List<ShowItemModel>
}
