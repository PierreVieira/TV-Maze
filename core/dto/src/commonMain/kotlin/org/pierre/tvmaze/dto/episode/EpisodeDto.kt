package org.pierre.tvmaze.dto.episode

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.pierre.tvmaze.dto.image.ImageDto

@Serializable
data class EpisodeDto(
    @SerialName("id") val id: Long?,
    @SerialName("name") val name: String?,
    @SerialName("number") val number: Int?,
    @SerialName("season") val season: Int?,
    @SerialName("image") val image: ImageDto?,
)
