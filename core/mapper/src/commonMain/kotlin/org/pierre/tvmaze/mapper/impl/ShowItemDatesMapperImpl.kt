package org.pierre.tvmaze.mapper.impl

import org.pierre.tvmaze.core.utils.safeYearOrNull
import org.pierre.tvmaze.mapper.ShowItemDatesMapper
import org.pierre.tvmaze.model.common.ShowItemDatesModel

internal class ShowItemDatesMapperImpl: ShowItemDatesMapper {
    override fun map(
        status: String?,
        premiered: String?,
        ended: String?,
    ): ShowItemDatesModel? {
        val startYear = premiered.safeYearOrNull()
        val endYear = ended.safeYearOrNull()
        return when {
            // Explicit Running (or TBD) with known start year
            status.equals("Running", ignoreCase = true) && startYear != null ->
                ShowItemDatesModel.Running(startYear)

            // Explicit Ended with both years
            status.equals("Ended", ignoreCase =true) && startYear != null && endYear != null ->
                ShowItemDatesModel.StartAndEnd(startYear, endYear)

            // No status but both dates available => Ended
            startYear != null && endYear != null ->
                ShowItemDatesModel.StartAndEnd(startYear, endYear)

            // Only start available => treat as Running (present)
            startYear != null ->
                ShowItemDatesModel.Running(startYear)

            else -> null
        }
    }
}
