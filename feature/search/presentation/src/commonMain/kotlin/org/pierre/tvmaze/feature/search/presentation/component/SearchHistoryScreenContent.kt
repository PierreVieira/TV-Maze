package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.feature.search.presentation.model.SearchContent
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent

@Composable
internal fun SearchHistoryScreenContent(
    modifier: Modifier = Modifier,
    model: SearchContent.History,
    onEvent: (SearchUiEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            SearchHistoryHeaderTitle(modifier = Modifier.padding(vertical = 8.dp))
        }
        items(
            items = model.data,
            key = { it.id },
        ) { item ->
            HistoryItemCard(
                modifier = Modifier
                    .fillMaxWidth(),
                id = item.id,
                query = item.query,
                range = item.searchedRange,
                onEvent = onEvent,
            )
        }
    }
}
