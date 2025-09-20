package org.pierre.tvmaze.components.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pierre.tvmaze.components.shimmer.model.ShimmerVariant
import org.pierre.tvmaze.model.data_status.DataStatus

@Composable
fun <T> DataStatus<T>.ToContent(
    modifier: Modifier,
    variant: ShimmerVariant = ShimmerVariant.Rectangle(),
    isAiShimmer: Boolean = false,
    content: @Composable (T) -> Unit,
) {
    when (this) {
        is DataStatus.Loaded -> content(data)
        is DataStatus.Loading -> ShimmerComponent(
            modifier = modifier,
            isAiShimmer = isAiShimmer,
            variant = variant,
        )
    }
}
