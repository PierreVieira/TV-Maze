package org.pierre.tvmaze.feature.episodes.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun LoadedSeasonNumberText(
    modifier: Modifier = Modifier,
    number: Int
) {
    Text(
        modifier = modifier,
        text = "Season $number",
        style = MaterialTheme.typography.titleMedium,
    )
}
