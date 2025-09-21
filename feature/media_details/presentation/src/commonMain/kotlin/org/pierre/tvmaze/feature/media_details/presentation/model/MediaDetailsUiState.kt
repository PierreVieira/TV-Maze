package org.pierre.tvmaze.feature.media_details.presentation.model

import org.pierre.tvmaze.model.common.media.MediaItemModel

data class MediaDetailsUiState(
    val itemModel: MediaItemModel,
    val isSummaryExpanded: Boolean
)
