package org.pierre.tvmaze.feature.media_details.presentation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyListScope
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
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiState
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer

private const val POSTER_WEIGHT = 0.42f
private const val DETAILS_WEIGHT = 0.58f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaDetailsScreen(
    state: MediaDetailsUiState,
    onEvent: (MediaDetailsUiEvent) -> Unit,
    itemsOnBottom: LazyListScope.() -> Unit,
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
                    state = state,
                    onEvent = onEvent,
                    itemsOnBottom = itemsOnBottom,
                )

                !isLargeScreen -> SmallScreenLayout(
                    state = state,
                    onEvent = onEvent,
                    itemsOnBottom = itemsOnBottom,
                )

                else -> TwoPaneRow(
                    state = state,
                    onEvent = onEvent,
                    itemsOnBottom = itemsOnBottom,
                )
            }
        }
    }
}

@Composable
private fun TwoPaneRow(
    state: MediaDetailsUiState,
    onEvent: (MediaDetailsUiEvent) -> Unit,
    itemsOnBottom: LazyListScope.() -> Unit,
) {
    Row(modifier = Modifier.fillMaxSize()) {
        MediaPosterSection(
            modifier = Modifier
                .weight(POSTER_WEIGHT)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            mediaItemModel = state.itemModel,
            onEvent = onEvent,
        )
        MediaDetailsContent(
            modifier = Modifier
                .weight(DETAILS_WEIGHT)
                .fillMaxSize(),
            state = state,
            onEvent = onEvent,
            itemsOnBottom = itemsOnBottom
        )
    }
}

@Composable
private fun SmallScreenLayout(
    state: MediaDetailsUiState,
    onEvent: (MediaDetailsUiEvent) -> Unit,
    itemsOnBottom: LazyListScope.() -> Unit,
) {
    MediaDetailsContent(
        modifier = Modifier.fillMaxSize(),
        itemsOnTop = {
            item {
                MediaPosterSection(
                    mediaItemModel = state.itemModel,
                    onEvent = onEvent,
                )
            }
            item {
                VerticalSpacer()
            }
        },
        state = state,
        onEvent = onEvent,
        itemsOnBottom = itemsOnBottom
    )
}
