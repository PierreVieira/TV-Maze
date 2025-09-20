package org.pierre.ui.components.show_item_card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.model.common.ShowItemModel

@Composable
fun ShowItemCardComponent(
    showItemModel: ShowItemModel,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = {},
        modifier = modifier,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            showItemModel.name.ToContent(
                modifier = Modifier.width(32.dp).height(24.dp)
            ) { name ->
                Text(name)
            }
        }
    }
}
