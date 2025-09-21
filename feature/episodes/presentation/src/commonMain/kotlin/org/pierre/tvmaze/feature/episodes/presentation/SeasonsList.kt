package org.pierre.tvmaze.feature.episodes.presentation

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.feature.episodes.presentation.component.SeasonBlock
import org.pierre.tvmaze.feature.episodes.presentation.model.SeasonsUiEvent
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer

fun LazyListScope.seasonsList(
    seasons: List<SeasonModel>,
    onEvent: (SeasonsUiEvent) -> Unit,
) {
    items(seasons) { season ->
        SeasonBlock(
            season = season,
            onEvent = onEvent,
        )
        HorizontalDivider()
    }
    item { VerticalSpacer(size = 24) }
}
