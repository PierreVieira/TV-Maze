package org.pierre.tvmaze.dto.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.pierre.tvmaze.dto.image.ImageDto

@Serializable
data class MediaDto(
    @SerialName("id") val id: Long?,
    @SerialName("name") val name: String?,
    @SerialName("image") val image: ImageDto?,
    @SerialName("type") val type: String?,
    @SerialName("genres") val genres: List<String>?,
    @SerialName("status") val status: String?,
    @SerialName("premiered") val premiered: String?,
    @SerialName("ended") val ended: String?,
    @SerialName("rating") val rating: RantingDto?,
    @SerialName("summary") val summary: String?,
)
