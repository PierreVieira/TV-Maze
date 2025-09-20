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
) {
    when (model) {
        is SearchContent.Error -> SearchErrorScreenContent(
            modifier = Modifier
                .widthIn(max = 600.dp)
                .fillMaxSize(),
            model = model
        )

        is SearchContent.SearchResults -> SearchResultsScreenContent(
            modifier = Modifier
                .weight(1f)
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp),
            model = model,
            onEvent = onEvent
        )

        is SearchContent.History -> SearchHistoryScreenContent(
            modifier = Modifier
                .weight(1f)
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp),
            model = model,
            onEvent = onEvent,
        )
    }
}
