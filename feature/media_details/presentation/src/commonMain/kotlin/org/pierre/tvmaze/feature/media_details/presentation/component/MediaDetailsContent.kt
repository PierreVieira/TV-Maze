package org.pierre.tvmaze.feature.media_details.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiEvent
import org.pierre.tvmaze.model.common.media.MediaItemModel
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer

@Composable
internal fun MediaDetailsContent(
    modifier: Modifier = Modifier,
    mediaItemModel: MediaItemModel,
    isSummaryExpanded: Boolean,
    onEvent: (MediaDetailsUiEvent) -> Unit,
    itemsOnTop: LazyListScope.() -> Unit = {},
    itemsOnBottom: LazyListScope.() -> Unit = {},
) {
    val horizontalPaddingModifier = Modifier.padding(horizontal = 16.dp)
    LazyColumn(modifier = modifier) {
        itemsOnTop()
        item {
            MediaDetailsTitle(
                name = mediaItemModel.name,
                modifier = horizontalPaddingModifier,
            )
        }
        item {
            VerticalSpacer(8)
        }
        item {
            mediaItemModel.dates?.let {
                MediaDetailsDatesRow(
                    dates = it,
                    modifier = horizontalPaddingModifier,
                )
            }
        }
        item {
            VerticalSpacer(8)
        }
        item {
            MediaDetailsGenresText(
                mediaItemModel = mediaItemModel,
                modifier = horizontalPaddingModifier,
            )
        }
        item {
            VerticalSpacer(12)
        }
        item {
            MediaDetailsStarsRow(mediaItemModel)
        }
        item {
            VerticalSpacer(12)
        }
        item {
            MediaDetailsSummaryText(
                modifier = horizontalPaddingModifier,
                mediaItemModel = mediaItemModel,
                isExpanded = isSummaryExpanded,
                onEvent = onEvent
            )
        }
        item {
            VerticalSpacer()
        }
        itemsOnBottom()
    }
}
