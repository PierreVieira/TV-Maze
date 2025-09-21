package org.pierre.tvmaze.feature.episodes.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import tvmaze.feature.episodes.presentation.generated.resources.Res
import tvmaze.feature.episodes.presentation.generated.resources.season

@Composable
internal fun LoadedSeasonNumberText(
    modifier: Modifier = Modifier,
    number: Int
) {
    Text(
        modifier = modifier,
        text = "${stringResource(Res.string.season)} $number",
        style = MaterialTheme.typography.titleMedium,
    )
}
