package org.pierre.tvmaze.feature.episodes.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.components.shimmer.ToContent
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.model.data_status.DataStatus

@Composable
fun SeasonNumberText(
    number: DataStatus<Int>,
    modifier: Modifier = Modifier,
) {
    number.ToContent(
        modifier = Modifier.fillMaxWidth(0.4f).height(16.dp),
        variant = ShimmerVariant.Rectangle()
    ) { loadedNumber ->
        LoadedSeasonNumberText(
            modifier = modifier,
            number = loadedNumber
        )
    }
}
