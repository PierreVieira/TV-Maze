package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.usecase.GetSearchItemsLoading
import org.pierre.tvmaze.model.common.ShowItemModel
import org.pierre.tvmaze.model.data_status.DataStatusUtils

internal class GetSearchItemsLoadingUseCase : GetSearchItemsLoading {
    override fun invoke(): List<ShowItemModel> {
        val loadingItem = DataStatusUtils.run {
            ShowItemModel(
                id = loading,
                name = loading,
                image = loading,
                stars = loading,
                isFavorite = loading,
                averageRanting = loading,
                dates = loading,
            )
        }
        return List(size = 8) { loadingItem }
    }
}
