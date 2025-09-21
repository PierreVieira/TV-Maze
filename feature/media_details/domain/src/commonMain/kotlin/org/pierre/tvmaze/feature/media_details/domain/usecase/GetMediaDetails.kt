package org.pierre.tvmaze.feature.media_details.domain.usecase

import org.pierre.tvmaze.model.common.media.MediaItemModel

fun interface GetMediaDetails {
    suspend operator fun invoke(id: Long): Result<MediaItemModel>
}
