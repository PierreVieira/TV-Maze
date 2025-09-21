package org.pierre.tvmaze.feature.media_details.data.repository

import io.ktor.client.request.get
import org.pierre.tvmaze.dto.MediaDto
import org.pierre.tvmaze.feature.media_details.domain.repository.MediaItemRepository
import org.pierre.tvmaze.mapper.MediaItemModelMapper
import org.pierre.tvmaze.model.common.MediaItemModel
import org.pierre.tvmaze.network.data.handler.RequestHandler

class MediaItemRepositoryImpl(
    private val mediaItemModelMapper: MediaItemModelMapper,
    private val requestHandler: RequestHandler,
) : MediaItemRepository {
    override suspend fun getDetails(id: Long): Result<MediaItemModel> =
        requestHandler.call<MediaDto> {
            get("/search/shows/$id")
        }.fold(
            onSuccess = { successResponse ->
                mediaItemModelMapper.map(successResponse)?.let {
                    Result.success(it)
                } ?: Result.failure(IllegalArgumentException("Invalid response"))
            },
            onFailure = { Result.failure(it) }
        )
}
