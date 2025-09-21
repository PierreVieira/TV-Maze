package org.pierre.tvmaze.feature.media_details.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.model.common.MediaItemModel
import org.pierre.tvmaze.ui.utils.blendOver
import org.pierre.tvmaze.ui.utils.dominantColorOf

@Composable
internal fun MediaPosterSection(
    mediaItemModel: MediaItemModel,
) {
    val neutralColor = MaterialTheme.colorScheme.surfaceVariant
    var backdropColor by remember { mutableStateOf(neutralColor) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backdropColor)
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        mediaItemModel.image?.ToContent(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp),
            variant = ShimmerVariant.Rectangle(RoundedCornerShape(12.dp)),
        ) { url ->
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .shadow(12.dp, RoundedCornerShape(12.dp), clip = false)
                    .clip(RoundedCornerShape(12.dp)),
                onSuccess = { success: AsyncImagePainter.State.Success ->
                    val image = success.result.image
                    if (image is ImageBitmap) {
                        backdropColor = dominantColorOf(image).blendOver(neutralColor)
                    }
                }
            )
        }
    }
}
