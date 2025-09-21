package org.pierre.tvmaze.feature.episodes.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.feature.episodes.presentation.model.SeasonsUiEvent

@Composable
internal fun SeasonEpisodesComponent(
    season: SeasonModel,
    onEvent: (SeasonsUiEvent) -> Unit,
) {
    season.episodes.forEach { episode ->
        Column {
            val onCheckedChange = { onEvent(SeasonsUiEvent.ToggleEpisodeWatched(episode)) }
            SeasonEpisodeRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCheckedChange() }
                    .padding(
                        horizontal = 16.dp,
                        vertical = 10.dp
                    ),
                episode = episode,
                onCheckedChange = onCheckedChange,
            )
        }
    }
}
