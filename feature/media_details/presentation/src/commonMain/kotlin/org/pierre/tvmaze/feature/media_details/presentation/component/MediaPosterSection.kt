package org.pierre.tvmaze.feature.media_details.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.model.common.MediaItemModel

@Composable
internal fun MediaPosterSection(
    mediaItemModel: MediaItemModel,
) {
    val neutralColor = MaterialTheme.colorScheme.surfaceVariant
    var backdropColor by remember { mutableStateOf(neutralColor) }

    val posterShape = RectangleShape

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backdropColor),
        contentAlignment = Alignment.Center
    ) {
        val baseModifier = Modifier
            .fillMaxWidth(0.72f)
            .aspectRatio(2f / 3f)
        mediaItemModel.image?.ToContent(
            modifier = baseModifier,
            variant = ShimmerVariant.Rectangle(posterShape),
        ) { url ->
            AsyncImage(
                model = url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = baseModifier
                    .graphicsLayer {
                        shape = posterShape
                        clip = true
                        shadowElevation = 12.dp.toPx()
                    },
            )
        }
    }
}
