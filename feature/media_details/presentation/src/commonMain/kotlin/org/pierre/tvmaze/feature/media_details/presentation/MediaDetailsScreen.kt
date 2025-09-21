package org.pierre.tvmaze.feature.media_details.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.feature.media_details.presentation.component.MediaPosterSection
import org.pierre.tvmaze.feature.media_details.presentation.component.mediaDetailsContent
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiEvent
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.common.media.MediaItemModel
import org.pierre.tvmaze.model.data_status.toLoadedInformation
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer

private const val POSTER_WEIGHT = 0.42f
private const val DETAILS_WEIGHT = 0.58f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaDetailsScreen(
    mediaItemModel: MediaItemModel,
    seasons: List<SeasonModel>,
    isSummaryExpanded: Boolean,
    onEvent: (MediaDetailsUiEvent) -> Unit,
    onEpisodeCheckedChange: (EpisodeModel) -> Unit,
) {
    // All seasons start collapsed on first render. We keep this state inside the composable.
    val initiallyCollapsed: Set<Int> = remember(seasons) {
        seasons.mapNotNull { it.number.toLoadedInformation()?.data }.toSet()
    }
    var collapsedSeasons: Set<Int> by remember(seasons) { mutableStateOf(initiallyCollapsed) }
    val onToggleSeason: (Int) -> Unit = { seasonNumber ->
        collapsedSeasons = if (collapsedSeasons.contains(seasonNumber)) {
            collapsedSeasons - seasonNumber
        } else {
            collapsedSeasons + seasonNumber
        }
    }
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            val isLargeScreen = maxWidth >= 600.dp
            val isLandscape = maxWidth > maxHeight

            when {
                isLandscape -> TwoPaneRow(
                    mediaItemModel = mediaItemModel,
                    seasons = seasons,
                    isSummaryExpanded = isSummaryExpanded,
                    onEvent = onEvent,
                    onEpisodeCheckedChange = onEpisodeCheckedChange,
                    onToggleSeason = onToggleSeason,
                    collapsedSeasons = collapsedSeasons
                )

                !isLargeScreen -> SmallScreenLayout(
                    mediaItemModel = mediaItemModel,
                    seasons = seasons,
                    isSummaryExpanded = isSummaryExpanded,
                    onEvent = onEvent,
                    onEpisodeCheckedChange = onEpisodeCheckedChange,
                    onToggleSeason = onToggleSeason,
                    collapsedSeasons = collapsedSeasons
                )

                else -> TwoPaneRow(
                    mediaItemModel = mediaItemModel,
                    seasons = seasons,
                    isSummaryExpanded = isSummaryExpanded,
                    onEvent = onEvent,
                    onEpisodeCheckedChange = onEpisodeCheckedChange,
                    onToggleSeason = onToggleSeason,
                    collapsedSeasons = collapsedSeasons
                )
            }
        }
    }
}

@Composable
private fun TwoPaneRow(
    mediaItemModel: MediaItemModel,
    seasons: List<SeasonModel>,
    isSummaryExpanded: Boolean,
    collapsedSeasons: Set<Int>,
    onEvent: (MediaDetailsUiEvent) -> Unit,
    onEpisodeCheckedChange: (EpisodeModel) -> Unit,
    onToggleSeason: (Int) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MediaPosterSection(
            modifier = Modifier
                .weight(POSTER_WEIGHT)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            mediaItemModel = mediaItemModel,
            onEvent = onEvent,
        )
        LazyColumn(
            Modifier
                .weight(DETAILS_WEIGHT)
                .fillMaxSize()
        ) {
            mediaDetailsContent(
                mediaItemModel = mediaItemModel,
                seasons = seasons,
                isSummaryExpanded = isSummaryExpanded,
                onEvent = onEvent,
                onEpisodeCheckedChange = onEpisodeCheckedChange,
                onToggleSeason = onToggleSeason,
                collapsedSeasons = collapsedSeasons
            )
        }
    }
}

@Composable
private fun SmallScreenLayout(
    mediaItemModel: MediaItemModel,
    seasons: List<SeasonModel>,
    isSummaryExpanded: Boolean,
    collapsedSeasons: Set<Int>,
    onEvent: (MediaDetailsUiEvent) -> Unit,
    onEpisodeCheckedChange: (EpisodeModel) -> Unit,
    onToggleSeason: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            MediaPosterSection(
                mediaItemModel = mediaItemModel,
                onEvent = onEvent,
            )
        }
        item {
            VerticalSpacer()
        }
        mediaDetailsContent(
            mediaItemModel = mediaItemModel,
            seasons = seasons,
            isSummaryExpanded = isSummaryExpanded,
            onEvent = onEvent,
            onEpisodeCheckedChange = onEpisodeCheckedChange,
            collapsedSeasons = collapsedSeasons,
            onToggleSeason = onToggleSeason,
        )
    }
}
