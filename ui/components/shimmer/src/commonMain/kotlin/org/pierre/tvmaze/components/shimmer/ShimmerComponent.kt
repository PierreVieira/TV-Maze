package org.pierre.tvmaze.components.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import org.pierre.tvmaze.components.shimmer.ai.aiShimmer
import org.pierre.tvmaze.components.shimmer.shimmer.shimmer

@Composable
fun ShimmerComponent(
    modifier: Modifier = Modifier,
    isAiShimmer: Boolean = false,
    shape: Shape = RectangleShape,
) {
    Box(
        modifier = modifier.clip(shape)
    ) {
        val baseSpacerModifier = Modifier.fillMaxSize()
        Spacer(
            modifier = if (isAiShimmer) {
                baseSpacerModifier.aiShimmer()
            } else {
                baseSpacerModifier.shimmer(isLoading = true)
            }
        )
    }
}
