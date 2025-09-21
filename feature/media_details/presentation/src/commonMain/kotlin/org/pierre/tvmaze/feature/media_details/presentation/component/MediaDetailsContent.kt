package org.pierre.tvmaze.feature.media_details.presentation.component

import androidx.compose.foundation.layout.Column
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
    LazyColumn(modifier = modifier) {
        itemsOnTop()
        item {
            Column(Modifier.padding(horizontal = 16.dp)) {
                MediaDetailsTitle(
                    name = mediaItemModel.name,
                )
                VerticalSpacer(8)
                mediaItemModel.dates?.let {
                    MediaDetailsDatesRow(
                        dates = it,
                    )
                }
                VerticalSpacer(8)
                MediaDetailsGenresText(
                    mediaItemModel = mediaItemModel,
                )
                VerticalSpacer(12)
                MediaDetailsStarsRow(mediaItemModel)
                VerticalSpacer(12)
                MediaDetailsSummaryText(
                    mediaItemModel = mediaItemModel,
                    isExpanded = isSummaryExpanded,
                    onEvent = onEvent
                )
                VerticalSpacer()
            }
        }
        itemsOnBottom()
    }
}
