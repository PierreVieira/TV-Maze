package org.pierre.tvmaze.feature.media_details.domain.usecase.impl

import org.pierre.tvmaze.feature.media_details.domain.repository.MediaItemRepository
import org.pierre.tvmaze.feature.media_details.domain.usecase.GetMediaDetails
import org.pierre.tvmaze.model.common.MediaItemModel

internal class GetMediaDetailsUseCase(
    private val repository: MediaItemRepository,
) : GetMediaDetails {
    override suspend fun invoke(id: Long): Result<MediaItemModel> = repository.getDetails(id)
}
