package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.usecase.GetSearchItemsLoading
import org.pierre.tvmaze.model.common.ShowItemModel
import org.pierre.tvmaze.model.data_status.DataStatusUtils

class GetSearchItemsLoadingUseCase : GetSearchItemsLoading {
    override fun invoke(): List<ShowItemModel> {
        val loadingItem = DataStatusUtils.run {
            ShowItemModel(
                id = 0L,
                name = loading,
                image = loading,
                premieredYear = loading,
                endedYear = loading,
                stars = loading,
                isFavorite = loading,
            )
        }
        return List(size = 8) { loadingItem }
    }
}
