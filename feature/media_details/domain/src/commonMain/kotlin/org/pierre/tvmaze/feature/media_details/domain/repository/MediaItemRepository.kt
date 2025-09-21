package org.pierre.tvmaze.feature.media_details.domain.repository

import org.pierre.tvmaze.model.common.MediaItemModel

interface MediaItemRepository {
    suspend fun getDetails(id: Long): Result<MediaItemModel>
}
