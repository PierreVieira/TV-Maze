package org.pierre.tvmaze.feature.media_details.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.model.common.media.StarsModel
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.ui.components.stars.StarsComponent

@Composable
internal fun MediaDetailsStarsRow(
    stars: DataStatus<StarsModel>,
    modifier: Modifier = Modifier,
) {
    stars.ToContent(
        modifier = modifier
            .fillMaxWidth(0.4f)
            .height(16.dp),
        variant = ShimmerVariant.StarsRow(spacing = 6.dp),
    ) { stars ->
        StarsComponent(
            modifier = modifier,
            starsModel = stars,
            starSize = 24.dp
        )
    }
}
