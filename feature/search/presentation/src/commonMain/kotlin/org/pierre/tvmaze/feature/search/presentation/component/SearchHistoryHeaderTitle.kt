package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import tvmaze.feature.search.presentation.generated.resources.Res
import tvmaze.feature.search.presentation.generated.resources.search_history_header_title

@Composable
internal fun SearchHistoryHeaderTitle(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(Res.string.search_history_header_title),
        style = MaterialTheme.typography.titleMedium,
    )
}
