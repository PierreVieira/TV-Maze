package org.pierre.tvmaze.feature.media_details.presentation.factory

import org.pierre.tvmaze.model.common.media.MediaItemModel

interface LoadingMediaItemsModelFactory {
    fun create(): MediaItemModel
}
