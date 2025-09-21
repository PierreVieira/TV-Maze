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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiEvent
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer
import tvmaze.feature.media_details.presentation.generated.resources.Res
import tvmaze.feature.media_details.presentation.generated.resources.media_details_show_less
import tvmaze.feature.media_details.presentation.generated.resources.media_details_show_more

@Composable
internal fun MediaDetailsSummaryText(
    modifier: Modifier = Modifier,
    summary: DataStatus<String>,
    isExpanded: Boolean,
    onEvent: (MediaDetailsUiEvent) -> Unit,
) {
    summary.ToContent(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        variant = ShimmerVariant.Rectangle(RoundedCornerShape(8.dp)),
    ) { overview ->
        Column(
            modifier = modifier
                .fillMaxWidth()
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
                Crossfade(
                    targetState = isExpanded,
                    label = "summary-action-crossfade"
                ) { expanded ->
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
