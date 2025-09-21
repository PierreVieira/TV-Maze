package org.pierre.tvmaze.feature.media_details.presentation.model

sealed interface MediaDetailsUiAction {
    data object NavigateBack : MediaDetailsUiAction
}
