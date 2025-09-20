package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.feature.search.presentation.model.SearchContent
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import org.pierre.ui.components.show_item_card.ShowItemCardComponent

@Composable
internal fun SearchResultsScreenContent(
    modifier: Modifier = Modifier,
    model: SearchContent.SearchResults,
    onEvent: (SearchUiEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(model.data) { searchItem ->
            val itemId = searchItem.id
            ShowItemCardComponent(
                modifier = Modifier.fillMaxWidth(),
                showItemModel = searchItem,
                onCardClick = { onEvent(SearchUiEvent.OnSearchResultItemClick(itemId)) },
                onFavoriteClick = { onEvent(SearchUiEvent.OnFavoriteSearchResultItemClick(itemId)) },
            )
        }
    }
}
