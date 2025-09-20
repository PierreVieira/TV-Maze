package org.pierre.tvmaze.components.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import org.pierre.tvmaze.model.data_status.DataStatus

@Composable
fun <T> DataStatus<T>.ToContent(
    modifier: Modifier,
    shape: Shape = RectangleShape,
    isAiShimmer: Boolean = false,
    content: @Composable (T) -> Unit,
) {
    when (this) {
        is DataStatus.Loaded -> content(data)
        is DataStatus.Loading -> ShimmerComponent(
            modifier = modifier,
            isAiShimmer = isAiShimmer,
            shape = shape,
        )
    }
}
