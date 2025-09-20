package org.pierre.tvmaze.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RantingDto(
    @SerialName("average") val average: Double?
)
