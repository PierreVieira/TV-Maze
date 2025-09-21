package org.pierre.tvmaze.feature.media_details.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiEvent
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiState
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer

@Composable
internal fun MediaDetailsContent(
    modifier: Modifier = Modifier,
    state: MediaDetailsUiState,
    onEvent: (MediaDetailsUiEvent) -> Unit,
    itemsOnTop: LazyListScope.() -> Unit = {},
    itemsOnBottom: LazyListScope.() -> Unit = {},
) {
    LazyColumn(modifier = modifier) {
        itemsOnTop()
        item {
            Column(Modifier.padding(horizontal = 16.dp)) {
                state.run {
                    MediaDetailsTitle(
                        name = itemModel.name,
                    )
                    itemModel.dates?.let {
                        VerticalSpacer(8)
                        MediaDetailsDatesRow(
                            dates = it,
                        )
                    }
                    itemModel.genres?.let { genres ->
                        VerticalSpacer(8)
                        MediaDetailsGenresText(genres)
                    }
                    itemModel.stars?.let { stars ->
                        VerticalSpacer(12)
                        MediaDetailsStarsRow(stars)
                    }
                    itemModel.summary?.let { summary ->
                        VerticalSpacer(12)
                        MediaDetailsSummaryText(
                            summary = summary,
                            isExpanded = isSummaryExpanded,
                            onEvent = onEvent
                        )
                    }
                    VerticalSpacer()
                }
            }
        }
        itemsOnBottom()
    }
}
