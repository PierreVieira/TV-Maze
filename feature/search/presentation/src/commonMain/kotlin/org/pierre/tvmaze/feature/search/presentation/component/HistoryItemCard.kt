package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import org.pierre.tvmaze.ui.components.icon_button.CommonIconButton
import org.pierre.tvmaze.ui.components.spacer.HorizontalSpacer
import tvmaze.feature.search.presentation.generated.resources.Res
import tvmaze.feature.search.presentation.generated.resources.delete_history_item

@Composable
internal fun HistoryItemCard(
    modifier: Modifier = Modifier,
    id: Long,
    query: String,
    range: IntRange,
    onEvent: (SearchUiEvent) -> Unit,
) {
    Card(
        modifier = modifier
            .combinedClickable(
                onClick = { onEvent(SearchUiEvent.OnHistoryItemClick(query)) },
                onLongClick = { onEvent(SearchUiEvent.OnHistoryItemLongClick(id)) },
            ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.History,
                contentDescription = query,
                modifier = Modifier.size(20.dp),
            )

            HorizontalSpacer(size = 12)

            HistoryText(
                query = query,
                range = range,
                modifier = Modifier.weight(1f).fillMaxWidth(),
            )

            CommonIconButton(
                onClick = { onEvent(SearchUiEvent.OnHistoryItemDeleteClick(id)) },
                contentDescription = stringResource(Res.string.delete_history_item, query),
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Outlined.Close,
            )
        }
    }
}
