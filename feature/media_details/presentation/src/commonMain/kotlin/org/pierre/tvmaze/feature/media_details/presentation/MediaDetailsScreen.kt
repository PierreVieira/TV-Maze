package org.pierre.tvmaze.feature.media_details.presentation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.feature.media_details.presentation.component.MediaDetailsContent
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
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            val isLargeScreen = maxWidth >= 600.dp
            val isLandscape = maxWidth > maxHeight

            if (isLandscape) {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.42f)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        MediaPosterSection(
                            mediaItemModel = mediaItemModel,
                            onEvent = onEvent,
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.58f)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        MediaDetailsContent(
                            mediaItemModel = mediaItemModel,
                            isSummaryExpanded = isSummaryExpanded,
                            onEvent = onEvent,
                        )
                    }
                }
            } else if (!isLargeScreen) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        MediaPosterSection(
                            mediaItemModel = mediaItemModel,
                            onEvent = onEvent,
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
            } else {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.42f)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        MediaPosterSection(
                            mediaItemModel = mediaItemModel,
                            onEvent = onEvent,
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.58f)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        MediaDetailsContent(
                            mediaItemModel = mediaItemModel,
                            isSummaryExpanded = isSummaryExpanded,
                            onEvent = onEvent,
                        )
                    }
                }
            }
        }
    }
}
