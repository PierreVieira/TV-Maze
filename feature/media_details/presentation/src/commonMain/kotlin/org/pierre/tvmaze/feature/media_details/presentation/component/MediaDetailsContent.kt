package org.pierre.tvmaze.feature.media_details.presentation.component

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.model.common.MediaItemDatesModel
import org.pierre.tvmaze.model.common.MediaItemModel
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer
import org.pierre.ui.components.stars.StarsComponent
import tvmaze.feature.media_details.presentation.generated.resources.Res
import tvmaze.feature.media_details.presentation.generated.resources.show_item_current

@Composable
internal fun MediaDetailsContent(
    mediaItemModel: MediaItemModel,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {
        Title(mediaItemModel)
        VerticalSpacer(8)
        DatesRow(mediaItemModel)
        VerticalSpacer(8)
        GenresText(mediaItemModel)
        VerticalSpacer(12)
        SummaryText(mediaItemModel)
        VerticalSpacer(12)
        StarsRow(mediaItemModel)
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
                text = "Início: ${dates.startYear}",
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
                text = "Fim: $endText",
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
private fun SummaryText(mediaItemModel: MediaItemModel) {
    mediaItemModel.summary?.ToContent(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        variant = ShimmerVariant.Rectangle(RoundedCornerShape(8.dp)),
    ) { overview ->
        Text(
            text = overview,
            style = MaterialTheme.typography.bodyLarge
        )
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
        StarsComponent(stars)
    }
}
