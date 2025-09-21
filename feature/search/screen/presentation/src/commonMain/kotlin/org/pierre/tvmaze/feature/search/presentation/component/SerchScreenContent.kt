package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.feature.search.presentation.model.SearchContent
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent

@Composable
internal fun ColumnScope.SearchScreenContent(
    model: SearchContent,
    onEvent: (SearchUiEvent) -> Unit,
    lastItem: @Composable () -> Unit = {},
) {
    val successContentModifier = Modifier
        .weight(1f)
        .widthIn(max = 400.dp)
        .padding(horizontal = 16.dp)
        .fillMaxWidth()
    when (model) {
        is SearchContent.SearchResults -> SearchResultsScreenContent(
            modifier = successContentModifier,
            model = model,
            onEvent = onEvent,
            lastItem = lastItem,
        )

        is SearchContent.History -> SearchHistoryScreenContent(
            modifier = successContentModifier,
            model = model,
            onEvent = onEvent,
            lastItem = lastItem,
        )

        is SearchContent.Error -> SearchErrorScreenContent(
            modifier = Modifier
                .widthIn(max = 600.dp)
                .fillMaxSize(),
            model = model
        )
    }
}
