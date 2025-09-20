package org.pierre.tvmaze.mapper.impl

import org.pierre.tvmaze.mapper.StarsMapper
import org.pierre.tvmaze.model.common.StarsModel
import kotlin.math.floor

class StarsMapperImpl : StarsMapper {
    override fun map(average: Double): StarsModel {
        // Clamp between 0.0 and 10.0, since TVMaze ratings are 0–10
        val safeAverage = average.coerceIn(
            minimumValue = 0.0,
            maximumValue = 10.0
        )

        // Scale to 0–5 range
        val starsValue = safeAverage / 2.0

        val fullStars = floor(starsValue).toInt()
        val showHalf = (starsValue - fullStars) >= 0.5

        return StarsModel(
            fullStarsAmount = fullStars,
            showInAHalf = showHalf
        )
    }
}
