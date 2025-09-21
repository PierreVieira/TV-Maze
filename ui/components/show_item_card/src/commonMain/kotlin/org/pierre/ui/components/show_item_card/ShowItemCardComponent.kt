package org.pierre.ui.components.show_item_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.model.common.ShowItemModel
import org.pierre.tvmaze.ui.components.icon_button.FavoriteIconButton
import org.pierre.tvmaze.ui.components.picture.PictureCommon
import org.pierre.tvmaze.ui.components.spacer.HorizontalSpacer
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer
import org.pierre.ui.components.stars.StarsComponent

@Composable
fun ShowItemCardComponent(
    showItemModel: ShowItemModel,
    onCardClick: (id: Long) -> Unit,
    onFavoriteClick: (id: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val id = showItemModel.id
    Card(
        onClick = { onCardClick(id) },
        modifier = modifier,
    ) {
        showItemModel.run {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                LeftContent(showItemModel)
                isFavorite.ToContent(
                    modifier = Modifier
                        .padding(
                            top = 16.dp,
                            end = 16.dp
                        )
                        .size(24.dp),
                    variant = ShimmerVariant.Heart(),
                ) { loadedIsFavorite ->
                    FavoriteIconButton(
                        isFavorite = loadedIsFavorite,
                        onClick = {
                            onFavoriteClick(id)
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun LeftContent(
    showItemModel: ShowItemModel,
    modifier: Modifier = Modifier,
) {
    showItemModel.run {
        Row(modifier = modifier) {
            val minImageHeight = 96.dp
            val imageModifier = Modifier
                .width(64.dp)
                .heightIn(min = minImageHeight)
            image?.ToContent(
                modifier = imageModifier,
            ) { safeImage ->
                PictureCommon(
                    modifier = imageModifier,
                    url = safeImage,
                    contentDescription = null,
                )
            }
            HorizontalSpacer(16)
            Column {
                VerticalSpacer(8)
                NameWithStars(
                    modifier = Modifier.fillMaxHeight(),
                    showItemModel = showItemModel,
                )
            }
        }
    }
}

@Composable
private fun NameWithStars(
    showItemModel: ShowItemModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        showItemModel.run {
            name.ToContent(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(12.dp),
                variant = ShimmerVariant.Rectangle(CircleShape),
            ) { loadedName ->
                Text(loadedName, fontWeight = FontWeight.Bold)
            }
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
            }
        }
    }
}
