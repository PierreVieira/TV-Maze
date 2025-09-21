package org.pierre.tvmaze.feature.episodes.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.model.common.episode.EpisodeModel

@Composable
internal fun SeasonEpisodeRow(
    modifier: Modifier = Modifier,
    episode: EpisodeModel,
    onCheckedChange: () -> Unit,
) {
    Row(
        modifier = modifier,
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
