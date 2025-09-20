package org.pierre.ui.components.show_item_card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.model.common.ShowItemModel
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer
import org.pierre.ui.components.stars.StarsComponent

@Composable
fun ShowItemCardComponent(
    showItemModel: ShowItemModel,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = {},
        modifier = modifier,
    ) {
        showItemModel.run {
            Column(modifier = Modifier.padding(16.dp)) {
                name.ToContent(
                    modifier = Modifier.fillMaxWidth(0.5f).height(12.dp),
                    shape = CircleShape,
                ) { loadedName ->
                    Text(loadedName)
                }
                stars?.run {
                    VerticalSpacer()
                    ToContent(
                        modifier = Modifier.fillMaxWidth(0.3f).height(8.dp),
                        shape = CircleShape,
                    ) { safeStars ->
                        StarsComponent(safeStars)
                    }
                }
            }
        }
    }
}
