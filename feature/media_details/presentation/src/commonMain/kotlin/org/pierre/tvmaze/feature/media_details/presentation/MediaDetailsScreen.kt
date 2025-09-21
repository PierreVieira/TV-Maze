package org.pierre.tvmaze.feature.media_details.presentation

import androidx.compose.foundation.layout.BoxWithConstraints
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
import org.pierre.tvmaze.model.common.media.MediaItemModel

private const val POSTER_WEIGHT = 0.42f
private const val DETAILS_WEIGHT = 0.58f

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

            when {
                isLandscape -> TwoPaneRow(
                    mediaItemModel = mediaItemModel,
                    isSummaryExpanded = isSummaryExpanded,
                    onEvent = onEvent,
                )

                !isLargeScreen -> SmallScreenLayout(
                    mediaItemModel = mediaItemModel,
                    isSummaryExpanded = isSummaryExpanded,
                    onEvent = onEvent
                )

                else -> TwoPaneRow(
                    mediaItemModel = mediaItemModel,
                    isSummaryExpanded = isSummaryExpanded,
                    onEvent = onEvent,
                )
            }
        }
    }
}

@Composable
private fun TwoPaneRow(
    mediaItemModel: MediaItemModel,
    isSummaryExpanded: Boolean,
    onEvent: (MediaDetailsUiEvent) -> Unit,
) {
    Row(modifier = Modifier.fillMaxSize()) {
        MediaPosterSection(
            modifier = Modifier
                .weight(POSTER_WEIGHT)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            mediaItemModel = mediaItemModel,
            onEvent = onEvent,
        )
        MediaDetailsContent(
            modifier = Modifier
                .weight(DETAILS_WEIGHT)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            mediaItemModel = mediaItemModel,
            isSummaryExpanded = isSummaryExpanded,
            onEvent = onEvent,
        )
    }
}

@Composable
private fun SmallScreenLayout(
    mediaItemModel: MediaItemModel,
    isSummaryExpanded: Boolean,
    onEvent: (MediaDetailsUiEvent) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
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
}
