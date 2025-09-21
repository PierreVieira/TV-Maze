package org.pierre.tvmaze.feature.media_details.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pierre.tvmaze.feature.media_details.presentation.component.MediaDetailsContent
import org.pierre.tvmaze.feature.media_details.presentation.component.MediaDetailsTopBar
import org.pierre.tvmaze.feature.media_details.presentation.component.MediaPosterSection
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiEvent
import org.pierre.tvmaze.model.common.MediaItemModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaDetailsScreen(
    mediaItemModel: MediaItemModel,
    isSummaryExpanded: Boolean,
    onEvent: (MediaDetailsUiEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            MediaDetailsTopBar(
                mediaItemModel = mediaItemModel,
                onEvent = onEvent,
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                MediaPosterSection(
                    mediaItemModel = mediaItemModel,
                )
            }
            item {
                MediaDetailsContent(
                    mediaItemModel = mediaItemModel,
                    isSummaryExpanded = isSummaryExpanded,
                    onEvent = onEvent,
                )
            }
        }
    }
}
