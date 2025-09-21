package org.pierre.tvmaze.dto.image

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    @SerialName("medium") val medium: String,
    @SerialName("original") val original: String
)
