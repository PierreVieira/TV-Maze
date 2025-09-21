package org.pierre.tvmaze.feature.episodes.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.model.data_status.mapToStatus

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
        val imageMediumStatus = episode.image?.mapToStatus { medium } ?:
                                episode.image?.mapToStatus { original }
        imageMediumStatus?.ToContent(modifier = Modifier.size(64.dp)) { url ->
            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = url,
                contentDescription = episode.name?.toLoadedData(),
                contentScale = ContentScale.Crop,
            )
        }

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
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
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
