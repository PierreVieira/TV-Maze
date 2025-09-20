package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.stringResource
import org.pierre.tvmaze.feature.search.presentation.model.SearchContent

@Composable
internal fun SearchErrorScreenContent(
    modifier: Modifier = Modifier,
    model: SearchContent.Error
) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(model.errorResourceId),
            style = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center),
        )
    }
}
