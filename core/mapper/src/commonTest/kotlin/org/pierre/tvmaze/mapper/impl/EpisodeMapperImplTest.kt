package org.pierre.tvmaze.mapper.impl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.pierre.tvmaze.dto.episode.EpisodeDto
import org.pierre.tvmaze.dto.image.ImageDto
import org.pierre.tvmaze.mapper.EpisodeMapper
import org.pierre.tvmaze.mapper.MediaModelMapper
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.common.image.ImagesModel
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class EpisodeMapperImplTest {

    private lateinit var mapper: EpisodeMapper

    @Test
    fun `given dto without id when map then returns null`() {
        prepareScenario()
        // Given
        val dto = EpisodeDto(
            id = null,
            name = "E1",
            number = 1,
            season = 1,
            image = null,
        )

        // When
        val result = mapper.map(dto = dto, mediaId = 10L, watchedIds = emptySet())

        // Then
        assertNull(result)
    }

    @Test
    fun `given dto with values when map then returns episode model with watched false`() {
        prepareScenario()
        // Given
        val dto = EpisodeDto(
            id = 100L,
            name = "Pilot",
            number = 1,
            season = 1,
            image = ImageDto(medium = "m.jpg", original = "o.jpg"),
        )

        // When
        val result = mapper.map(dto = dto, mediaId = 10L, watchedIds = emptySet())

        // Then
        val expected = EpisodeModel(
            id = 100L.toLoadedStatus(),
            mediaId = 10L,
            name = "Pilot".toLoadedStatus(),
            season = 1.toLoadedStatus(),
            number = 1.toLoadedStatus(),
            image = ImagesModel(medium = "m.jpg", original = "o.jpg").toLoadedStatus(),
            isWatched = false.toLoadedStatus(),
        )
        assertEquals(expected, result)
    }

    @Test
    fun `given watched id set contains episode id when map then returns isWatched true`() {
        prepareScenario()
        // Given
        val dto = EpisodeDto(
            id = 200L,
            name = "Finale",
            number = 10,
            season = 2,
            image = null,
        )

        // When
        val result = mapper.map(dto = dto, mediaId = 77L, watchedIds = setOf(200L))

        // Then
        val expected = EpisodeModel(
            id = 200L.toLoadedStatus(),
            mediaId = 77L,
            name = "Finale".toLoadedStatus(),
            season = 2.toLoadedStatus(),
            number = 10.toLoadedStatus(),
            image = null,
            isWatched = true.toLoadedStatus(),
        )
        assertEquals(expected, result)
    }

    private fun prepareScenario(
        mediaModelMapper: MediaModelMapper = MediaModelMapper { dto -> ImagesModel(dto.medium, dto.original) }
    ) {
        mapper = EpisodeMapperImpl(mediaModelMapper)
    }
}
