package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.pierre.tvmaze.feature.search.presentation.model.SearchContent
import org.pierre.tvmaze.feature.search.presentation.model.SearchState
import org.pierre.ui.components.show_item_card.ShowItemCardComponent
import tvmaze.feature.search.presentation.generated.resources.Res
import tvmaze.feature.search.presentation.generated.resources.no_history_message
import tvmaze.feature.search.presentation.generated.resources.no_results

private val maxScreenWidth = 400.dp
@Composable
fun ColumnScope.ScreenContent(state: SearchState) {
    when (state.content) {
        SearchContent.NoHistory -> Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(Res.string.no_history_message),
                style = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center),
            )
        }
        SearchContent.NoResults -> Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(Res.string.no_results),
                style = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center),
            )
        }

        is SearchContent.SearchResult -> LazyColumn(
            modifier = Modifier
                .weight(1f)
                .widthIn(max = maxScreenWidth)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(state.content.searchItems) { searchItem ->
                ShowItemCardComponent(
                    modifier = Modifier.fillMaxWidth(),
                    showItemModel = searchItem,
                    onCardClick = {},
                    onFavoriteClick = {}
                )
            }
        }
    }
}
