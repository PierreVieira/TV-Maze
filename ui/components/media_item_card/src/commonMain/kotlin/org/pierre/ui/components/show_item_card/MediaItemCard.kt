package org.pierre.ui.components.show_item_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.model.common.media.MediaItemModel
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.ui.components.icon_button.FavoriteIconButton

@Composable
fun MediaItemCard(
    mediaItemModel: MediaItemModel,
    onCardClick: (id: Long) -> Unit,
    onFavoriteClick: (id: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val loadedId = mediaItemModel.id.toLoadedData()
    Card(
        onClick = { loadedId?.let(onCardClick) },
        modifier = modifier,
    ) {
        mediaItemModel.run {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                MediaItemCardLeftContent(
                    mediaItemModel = mediaItemModel,
                    modifier = Modifier.weight(1f),
                )
                isFavorite.ToContent(
                    modifier = Modifier
                        .padding(
                            top = 16.dp,
                            end = 16.dp,
                        )
                        .size(24.dp),
                    variant = ShimmerVariant.Heart(),
                ) { loadedIsFavorite ->
                    FavoriteIconButton(
                        isFavorite = loadedIsFavorite,
                        onClick = {
                            loadedId?.let(onFavoriteClick)
                        },
                    )
                }
            }
        }
    }
}
