package org.pierre.ui.components.media_item_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.model.common.MediaItemDatesModel
import org.pierre.tvmaze.model.common.MediaItemCard
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer
import org.pierre.ui.components.stars.StarsComponent
import tvmaze.ui.components.media_item_card.generated.resources.Res
import tvmaze.ui.components.media_item_card.generated.resources.show_item_current
import tvmaze.ui.components.media_item_card.generated.resources.show_item_no_dates
import tvmaze.ui.components.media_item_card.generated.resources.show_item_no_stars

@Composable
internal fun NameWithDateAndStars(
    mediaItemCard: MediaItemCard,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        mediaItemCard.run {
            name.ToContent(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(12.dp),
            ) { loadedName ->
                Text(
                    text = loadedName,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            dates?.run {
                VerticalSpacer(4)
                ToContent(
                    modifier = Modifier
                        .fillMaxWidth(0.35f)
                        .height(10.dp),
                ) { datesModel ->
                    val datesText = when (datesModel) {
                        is MediaItemDatesModel.Running ->
                            "${datesModel.startYear} - ${stringResource(Res.string.show_item_current)}"
                        is MediaItemDatesModel.StartAndEnd ->
                            "${datesModel.startYear} - ${datesModel.endYear}"
                    }
                    Text(datesText)
                }
            } ?: Text(
                text = stringResource(Res.string.show_item_no_dates),
                style = MaterialTheme.typography.bodySmall,
            )

            stars?.run {
                VerticalSpacer(8)
                ToContent(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(8.dp),
                    variant = ShimmerVariant.StarsRow(spacing = 4.dp),
                ) { safeStars ->
                    StarsComponent(safeStars)
                }
            } ?: Text(
                text = stringResource(Res.string.show_item_no_stars),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
