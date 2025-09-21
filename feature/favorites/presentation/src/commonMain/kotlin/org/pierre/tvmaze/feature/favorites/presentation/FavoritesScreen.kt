package org.pierre.tvmaze.feature.favorites.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.feature.favorites.presentation.model.FavoritesUiEvent
import org.pierre.tvmaze.model.common.MediaItemModel
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.ui.components.show_item_card.MediaItemCard

@Composable
fun FavoritesScreen(
    items: List<MediaItemModel>,
    onEvent: (FavoritesUiEvent) -> Unit,
) {
    Scaffold { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                items = items,
                key = { item ->
                    item.run {
                        id.toLoadedData() ?: name.toLoadedData() ?: hashCode()
                    }
                }
            ) { item ->
                MediaItemCard(
                    modifier = Modifier.fillMaxWidth(),
                    mediaItemModel = item,
                    onCardClick = { safeId ->
                        onEvent(FavoritesUiEvent.OnItemClick(safeId))
                    },
                    onFavoriteClick = { safeId ->
                        onEvent(FavoritesUiEvent.OnFavoriteItemClick(safeId))
                    },
                )
            }
        }
    }
}
