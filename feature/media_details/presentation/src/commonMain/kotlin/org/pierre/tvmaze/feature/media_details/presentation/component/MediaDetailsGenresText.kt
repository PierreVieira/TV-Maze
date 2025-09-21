package org.pierre.tvmaze.feature.media_details.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.model.common.media.MediaItemModel

@Composable
internal fun MediaDetailsGenresText(
    mediaItemModel: MediaItemModel,
    modifier: Modifier = Modifier,
) {
    mediaItemModel.genres?.ToContent(
        modifier = modifier
            .fillMaxWidth(0.6f)
            .height(16.dp),
        variant = ShimmerVariant.Rectangle(RoundedCornerShape(6.dp)),
    ) { genres ->
        Text(
            modifier = modifier,
            text = genres.joinToString(" · "),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
