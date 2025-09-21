package org.pierre.tvmaze.feature.media_details.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.model.common.media.MediaItemDatesModel
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.tvmaze.ui.components.spacer.HorizontalSpacer
import tvmaze.feature.media_details.presentation.generated.resources.Res
import tvmaze.feature.media_details.presentation.generated.resources.media_details_end_label
import tvmaze.feature.media_details.presentation.generated.resources.media_details_start_label
import tvmaze.feature.media_details.presentation.generated.resources.show_item_current

@Composable
internal fun MediaDetailsDatesRow(
    dates: DataStatus<MediaItemDatesModel>,
    modifier: Modifier = Modifier,
) {
    dates.ToContent(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .height(16.dp),
        variant = ShimmerVariant.Rectangle(RoundedCornerShape(6.dp)),
    ) { dates ->
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Event,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            HorizontalSpacer(size = 6)
            Text(
                text = "${stringResource(Res.string.media_details_start_label)} ${dates.startYear}",
                style = MaterialTheme.typography.bodyMedium
            )

            HorizontalSpacer()

            Icon(
                imageVector = Icons.Outlined.Event,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            HorizontalSpacer(size = 6)
            val endText = when (dates) {
                is MediaItemDatesModel.Running -> stringResource(Res.string.show_item_current)
                is MediaItemDatesModel.StartAndEnd -> dates.endYear.toString()
            }
            Text(
                text = "${
                    (stringResource(Res.string.media_details_end_label) + " ").takeUnless {
                        dates is MediaItemDatesModel.Running
                    }.orEmpty()
                }$endText",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
