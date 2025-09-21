package org.pierre.tvmaze.feature.media_details.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiEvent
import org.pierre.tvmaze.model.common.MediaItemModel
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.ui.components.icon_button.ArrowBackIconButton
import org.pierre.tvmaze.ui.components.icon_button.FavoriteIconButton

@Composable
internal fun MediaPosterSection(
    modifier: Modifier = Modifier,
    mediaItemModel: MediaItemModel,
    onEvent: (MediaDetailsUiEvent) -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        val posterWidthFraction = 0.72f

        val posterModifier = Modifier
            .fillMaxWidth(posterWidthFraction)
            .aspectRatio(2f / 3f)
            .heightIn(max = maxHeight / 2)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = maxHeight / 2),
            contentAlignment = Alignment.Center
        ) {
            PosterImage(
                mediaItemModel = mediaItemModel,
                modifier = posterModifier,
            )
            PosterActionsOverlay(mediaItemModel = mediaItemModel, onEvent = onEvent)
        }
    }
}

@Composable
private fun PosterImage(
    mediaItemModel: MediaItemModel,
    modifier: Modifier,
) {
    mediaItemModel.images?.ToContent(
        modifier = modifier
            .graphicsLayer {
                this.shape = shape
                clip = true
                shadowElevation = 12.dp.toPx()
            },
        variant = ShimmerVariant.Rectangle(RectangleShape),
    ) { images ->
        AsyncImage(
            model = images.original,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }
}

@Composable
private fun BoxScope.PosterActionsOverlay(
    mediaItemModel: MediaItemModel,
    onEvent: (MediaDetailsUiEvent) -> Unit,
) {
    val loadedId = mediaItemModel.id.toLoadedData()
    Row(
        modifier = Modifier
            .align(Alignment.TopStart)
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ArrowBackIconButton(onClick = { onEvent(MediaDetailsUiEvent.OnBackClick) })

        Box(modifier = Modifier.weight(1f))

        mediaItemModel.isFavorite.ToContent(
            modifier = Modifier
                .padding(end = 0.dp)
                .size(24.dp),
            variant = ShimmerVariant.Heart(),
        ) { isFav ->
            FavoriteIconButton(
                isFavorite = isFav,
                onClick = { loadedId?.let { onEvent(MediaDetailsUiEvent.OnFavoriteClick(it)) } },
            )
        }
    }
}
