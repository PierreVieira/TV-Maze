package org.pierre.tvmaze.feature.media_details.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiEvent
import org.pierre.tvmaze.model.common.MediaItemDatesModel
import org.pierre.tvmaze.model.common.MediaItemModel
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer
import org.pierre.ui.components.stars.StarsComponent
import tvmaze.feature.media_details.presentation.generated.resources.Res
import tvmaze.feature.media_details.presentation.generated.resources.show_item_current
import tvmaze.feature.media_details.presentation.generated.resources.media_details_start_label
import tvmaze.feature.media_details.presentation.generated.resources.media_details_end_label
import tvmaze.feature.media_details.presentation.generated.resources.media_details_show_less
import tvmaze.feature.media_details.presentation.generated.resources.media_details_show_more

@Composable
internal fun MediaDetailsContent(
    modifier: Modifier = Modifier,
    mediaItemModel: MediaItemModel,
    isSummaryExpanded: Boolean,
    onEvent: (MediaDetailsUiEvent) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {
        Title(mediaItemModel)
        VerticalSpacer(8)
        DatesRow(mediaItemModel)
        VerticalSpacer(8)
        GenresText(mediaItemModel)
        VerticalSpacer(12)
        StarsRow(mediaItemModel)
        VerticalSpacer(12)
        SummaryText(mediaItemModel, isSummaryExpanded, onEvent)
    }
}

@Composable
private fun Title(mediaItemModel: MediaItemModel) {
    mediaItemModel.name.ToContent(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .height(24.dp),
        variant = ShimmerVariant.Rectangle(RoundedCornerShape(6.dp)),
    ) { title ->
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun DatesRow(mediaItemModel: MediaItemModel) {
    mediaItemModel.dates?.ToContent(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(16.dp),
        variant = ShimmerVariant.Rectangle(RoundedCornerShape(6.dp)),
    ) { dates ->
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.Event,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "${stringResource(Res.string.media_details_start_label)} ${dates.startYear}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.width(16.dp))

            Icon(
                imageVector = Icons.Outlined.Event,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(6.dp))
            val endText = when (dates) {
                is MediaItemDatesModel.Running -> stringResource(Res.string.show_item_current)
                is MediaItemDatesModel.StartAndEnd -> dates.endYear.toString()
            }
            Text(
                text = "${
                    stringResource(Res.string.media_details_end_label).takeUnless {
                        dates is MediaItemDatesModel.Running
                    }.orEmpty()
                } $endText",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun GenresText(mediaItemModel: MediaItemModel) {
    mediaItemModel.genres?.ToContent(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .height(16.dp),
        variant = ShimmerVariant.Rectangle(RoundedCornerShape(6.dp)),
    ) { list ->
        Text(
            text = list.joinToString(" · "),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun SummaryText(
    mediaItemModel: MediaItemModel,
    isExpanded: Boolean,
    onEvent: (MediaDetailsUiEvent) -> Unit,
) {
    mediaItemModel.summary?.ToContent(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        variant = ShimmerVariant.Rectangle(RoundedCornerShape(8.dp)),
    ) { overview ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                // Animate the size change when maxLines flips 3 <-> MAX
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                )
        ) {
            var isOverflowing by remember { mutableStateOf(false) }

            AnimatedContent(
                targetState = isExpanded,
                transitionSpec = {
                    (fadeIn() + expandVertically()) togetherWith
                            (fadeOut() + shrinkVertically())
                },
                label = "summary-expand-collapse"
            ) { expanded ->
                Text(
                    text = overview,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = if (expanded) Int.MAX_VALUE else 3,
                    overflow = if (expanded) TextOverflow.Visible else TextOverflow.Ellipsis,
                    onTextLayout = { layoutResult ->
                        val overflow = layoutResult.lineCount > 3 || layoutResult.hasVisualOverflow
                        if (overflow != isOverflowing) isOverflowing = overflow
                    }
                )
            }

            AnimatedVisibility(
                visible = isOverflowing || isExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                VerticalSpacer(4)
                Crossfade(targetState = isExpanded, label = "summary-action-crossfade") { expanded ->
                    val actionText = stringResource(
                        if (expanded) Res.string.media_details_show_less
                        else Res.string.media_details_show_more
                    )
                    Text(
                        text = actionText,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable { onEvent(MediaDetailsUiEvent.OnToggleSummaryExpansion) }
                    )
                }
            }
        }
    }
}

@Composable
private fun StarsRow(mediaItemModel: MediaItemModel) {
    mediaItemModel.stars?.ToContent(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .height(16.dp),
        variant = ShimmerVariant.StarsRow(spacing = 6.dp),
    ) { stars ->
        StarsComponent(
            starsModel = stars,
            starSize = 24.dp
        )
    }
}
