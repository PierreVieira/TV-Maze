package org.pierre.tvmaze.components.shimmer.ai

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.pierre.tvmaze.components.shimmer.GradientShimmerModel
import org.pierre.tvmaze.components.shimmer.shimmer.shimmer

fun Modifier.aiShimmer(): Modifier {
    val gradientColors = GradientShimmerModel(
        startColor = Color(0xFFD8E7FF),
        endColor = Color(0xFFE7D8FF),
    )
    return shimmer(
        isLoading = true,
        gradientShimmerModel = gradientColors,
        color = gradientColors.endColor.copy(alpha = 0.6f),
    )
}
