package org.pierre.tvmaze.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowResultDto(
    @SerialName("score") val score: Double,
    @SerialName("show") val show: ShowDto
)
