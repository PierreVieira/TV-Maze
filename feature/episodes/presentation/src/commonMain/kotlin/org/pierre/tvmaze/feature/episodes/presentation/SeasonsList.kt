package org.pierre.tvmaze.feature.episodes.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.feature.episodes.presentation.model.SeasonsUiEvent
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.tvmaze.ui.components.icon_button.CommonIconButton
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

@Composable
internal fun SeasonBlock(
    season: SeasonModel,
    onEvent: (SeasonsUiEvent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val isCollapsed = season.isCollapsed
        SeasonHeader(
            number = season.number,
            isCollapsed = isCollapsed,
            onToggleSeason = { onEvent(SeasonsUiEvent.ToggleSeason(it)) },
        )
        if (!isCollapsed) {
            season.episodes.forEach { episode ->
                EpisodeRow(
                    episode = episode,
                    onCheckedChange = { onEvent(SeasonsUiEvent.ToggleEpisodeWatched(episode)) },
                )
            }
        }
    }
}

@Composable
private fun SeasonHeader(
    number: DataStatus<Int>,
    isCollapsed: Boolean,
    onToggleSeason: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = number is DataStatus.Loaded) {
                (number as? DataStatus.Loaded)?.data?.let(onToggleSeason)
            }
            .padding(
                horizontal = 8.dp,
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        val isLoaded = number is DataStatus.Loaded
        val icon = if (isCollapsed) {
            Icons.AutoMirrored.Filled.KeyboardArrowRight
        } else {
            Icons.Filled.KeyboardArrowDown
        }
        CommonIconButton(
            imageVector = icon,
            contentDescription = if (isCollapsed) "Expand season" else "Collapse season",
            onClick = {
                if (isLoaded) {
                    number.data.let(onToggleSeason)
                }
            }
        )
        number.ToContent(modifier = Modifier) { loadedNumber ->
            Text(
                text = "Season $loadedNumber",
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
private fun EpisodeRow(
    episode: EpisodeModel,
    onCheckedChange: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange() }
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                episode.number?.ToContent(modifier = Modifier) { n ->
                    Text(
                        text = "E${n}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                episode.name?.ToContent(modifier = Modifier) { name ->
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }

        val watchedStatus = episode.isWatched
        watchedStatus.ToContent(modifier = Modifier) { checked ->
            Checkbox(
                checked = checked,
                onCheckedChange = { onCheckedChange() },
            )
        }
    }
}
