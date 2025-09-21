package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.core.utils.isLastIndex
import org.pierre.tvmaze.feature.search.presentation.model.SearchContent
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.ui.components.show_item_card.MediaItemCard

@Composable
internal fun SearchResultsScreenContent(
    modifier: Modifier = Modifier,
    model: SearchContent.SearchResults,
    onEvent: (SearchUiEvent) -> Unit,
    lastItem: @Composable (() -> Unit),
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(model.data) { index, searchItem ->
            val itemId = searchItem.id.toLoadedData()
            MediaItemCard(
                modifier = Modifier.fillMaxWidth(),
                mediaItemModel = searchItem,
                onCardClick = {
                    itemId?.let { onEvent(SearchUiEvent.OnSearchResultItemClick(it)) }
                },
                onFavoriteClick = {
                    itemId?.let {
                        onEvent(SearchUiEvent.OnFavoriteSearchResultItemClick(it))
                    }
                },
            )
            if (model.data.isLastIndex(index)) {
                lastItem()
            }
        }
    }
}
