package org.pierre.tvmaze.mapper

import org.pierre.tvmaze.model.common.media.StarsModel

fun interface StarsMapper {
    fun map(average: Double): StarsModel
}
