package org.pierre.tvmaze.feature.media_details.presentation.factory.impl

import org.pierre.tvmaze.feature.media_details.presentation.factory.LoadingMediaItemsModelFactory
import org.pierre.tvmaze.model.common.media.MediaItemModel
import org.pierre.tvmaze.model.data_status.DataStatusUtils

internal class LoadingMediaItemsModelFactoryImpl : LoadingMediaItemsModelFactory {
    override fun create(): MediaItemModel = DataStatusUtils.run {
        MediaItemModel(
            id = loading,
            name = loading,
            images = loading,
            dates = loading,
            stars = loading,
            isFavorite = loading,
            averageRanting = loading,
            summary = loading,
            genres = loading,
        )
    }
}
