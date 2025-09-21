package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.search.domain.usecase.GetSearchItemsLoading
import org.pierre.tvmaze.model.common.MediaItemCard
import org.pierre.tvmaze.model.data_status.DataStatusUtils

internal class GetSearchItemsLoadingUseCase : GetSearchItemsLoading {
    override fun invoke(): List<MediaItemCard> {
        val loadingItem = DataStatusUtils.run {
            MediaItemCard(
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
