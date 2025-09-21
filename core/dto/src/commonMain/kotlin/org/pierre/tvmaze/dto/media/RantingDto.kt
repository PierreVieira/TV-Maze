package org.pierre.tvmaze.dto.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RantingDto(
    @SerialName("average") val average: Double?
)
