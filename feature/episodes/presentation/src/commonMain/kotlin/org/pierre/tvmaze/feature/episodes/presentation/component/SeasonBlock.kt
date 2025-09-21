package org.pierre.tvmaze.feature.episodes.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.feature.episodes.presentation.model.SeasonsUiEvent

@Composable
internal fun SeasonBlock(
    season: SeasonModel,
    onEvent: (SeasonsUiEvent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val isCollapsed = season.isCollapsed
        val number = season.number
        SeasonHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                    vertical = 12.dp
                ),
            number = number,
            isCollapsed = isCollapsed,
            onToggleSeason = { onEvent(SeasonsUiEvent.ToggleSeason(it)) },
        )
        if (!isCollapsed) {
            SeasonEpisodesComponent(season, onEvent)
        }
    }
}
