package org.pierre.tvmaze.mapper.impl

import org.pierre.tvmaze.core.utils.safeYearOrNull
import org.pierre.tvmaze.mapper.MediaItemDatesMapper
import org.pierre.tvmaze.model.common.media.MediaItemDatesModel

internal class MediaItemDatesMapperImpl: MediaItemDatesMapper {
    override fun map(
        status: String?,
        premiered: String?,
        ended: String?,
    ): MediaItemDatesModel? {
        val startYear = premiered.safeYearOrNull()
        val endYear = ended.safeYearOrNull()
        return when {
            // Explicit Running (or TBD) with known start year
            status.equals("Running", ignoreCase = true) && startYear != null ->
                MediaItemDatesModel.Running(startYear)

            // Explicit Ended with both years
            status.equals("Ended", ignoreCase =true) && startYear != null && endYear != null ->
                MediaItemDatesModel.StartAndEnd(startYear, endYear)

            // No status but both dates available => Ended
            startYear != null && endYear != null ->
                MediaItemDatesModel.StartAndEnd(startYear, endYear)

            // Only start available => treat as Running (present)
            startYear != null ->
                MediaItemDatesModel.Running(startYear)

            else -> null
        }
    }
}
