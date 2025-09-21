package org.pierre.ui.components.show_item_card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.model.common.media.MediaItemModel
import org.pierre.tvmaze.ui.components.picture.PictureCommon
import org.pierre.tvmaze.ui.components.spacer.HorizontalSpacer
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer
import org.pierre.ui.components.media_item_card.NameWithDateAndStars

@Composable
internal fun MediaItemCardLeftContent(
    mediaItemModel: MediaItemModel,
    modifier: Modifier = Modifier,
) {
    mediaItemModel.run {
        Row(modifier = modifier) {
            val minImageHeight = 96.dp
            val imageModifier = Modifier
                .width(64.dp)
                .heightIn(min = minImageHeight)
            images?.ToContent(
                modifier = imageModifier,
            ) { safeImages ->
                PictureCommon(
                    modifier = imageModifier,
                    url = safeImages.medium,
                    contentDescription = null,
                )
            } ?: Spacer(
                modifier = imageModifier
            )
            HorizontalSpacer(16)
            Column {
                VerticalSpacer(8)
                NameWithDateAndStars(
                    modifier = Modifier.fillMaxHeight(),
                    mediaItemModel = mediaItemModel,
                )
            }
        }
    }
}
