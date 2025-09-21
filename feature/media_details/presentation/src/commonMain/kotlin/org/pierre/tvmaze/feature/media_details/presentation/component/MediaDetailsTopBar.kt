package org.pierre.tvmaze.feature.media_details.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiEvent
import org.pierre.tvmaze.model.common.MediaItemModel
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.ui.components.icon_button.ArrowBackIconButton
import org.pierre.tvmaze.ui.components.icon_button.FavoriteIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MediaDetailsTopBar(
    mediaItemModel: MediaItemModel,
    onEvent: (MediaDetailsUiEvent) -> Unit,
) {
    val loadedId = mediaItemModel.id.toLoadedData()
    CenterAlignedTopAppBar(
        title = {
            mediaItemModel.name.ToContent(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(20.dp),
                variant = ShimmerVariant.Rectangle(RoundedCornerShape(6.dp)),
            ) { title ->
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
        navigationIcon = { ArrowBackIconButton(onClick = { onEvent(MediaDetailsUiEvent.OnBackClick) }) },
        actions = {
            mediaItemModel.isFavorite.ToContent(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp),
                variant = ShimmerVariant.Heart(),
            ) { isFav ->
                FavoriteIconButton(
                    isFavorite = isFav,
                    onClick = { loadedId?.let { onEvent(MediaDetailsUiEvent.OnFavoriteClick(it)) } },
                )
            }
        }
    )
}
