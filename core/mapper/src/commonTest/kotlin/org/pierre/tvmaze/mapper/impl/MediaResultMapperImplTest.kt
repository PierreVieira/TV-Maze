package org.pierre.tvmaze.mapper.impl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.pierre.tvmaze.dto.image.ImageDto
import org.pierre.tvmaze.dto.media.MediaDto
import org.pierre.tvmaze.dto.media.MediaResultDto
import org.pierre.tvmaze.dto.media.RantingDto
import org.pierre.tvmaze.mapper.MediaItemModelMapper
import org.pierre.tvmaze.model.common.image.ImagesModel
import org.pierre.tvmaze.model.common.media.MediaItemDatesModel
import org.pierre.tvmaze.model.common.media.MediaItemModel
import org.pierre.tvmaze.model.common.media.StarsModel
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class MediaResultMapperImplTest {

    private lateinit var mapper: MediaResultMapperImpl

    @Test
    fun `given media result dto when map then returns mapped media item`() {
        // Given
        val dto = MediaResultDto(
            score = 1.23,
            media = MediaDto(
                id = 7L,
                name = "Title",
                image = ImageDto("m.jpg", "o.jpg"),
                type = "Scripted",
                genres = listOf("Drama"),
                status = "Running",
                premiered = "2020-01-01",
                ended = null,
                rating = RantingDto(average = 6.0),
                summary = "<p>Desc</p>",
            )
        )
        val expected = MediaItemModel(
            id = 7L.toLoadedStatus(),
            name = "Title".toLoadedStatus(),
            images = ImagesModel("m.jpg", "o.jpg").toLoadedStatus(),
            dates = MediaItemDatesModel.Running(2020).toLoadedStatus(),
            stars = StarsModel(3, false).toLoadedStatus(),
            isFavorite = false.toLoadedStatus(),
            averageRanting = 6.0.toLoadedStatus(),
            summary = "Desc".toLoadedStatus(),
            genres = DataStatus.Loaded(listOf("Drama"))
        )
        val fakeMediaItemMapper = MediaItemModelMapper { expected }
        prepareScenario(fakeMediaItemMapper)

        // When
        val model = mapper.map(dto)

        // Then
        assertEquals(expected, model)
    }

    @Test
    fun `given media result dto with null id when map then returns null`() {
        // Given
        val dto = MediaResultDto(
            score = 0.1,
            media = MediaDto(
                id = null,
                name = null,
                image = null,
                type = null,
                genres = null,
                status = null,
                premiered = null,
                ended = null,
                rating = null,
                summary = null,
            )
        )

        prepareScenario { null }

        // When
        val model = mapper.map(dto)

        // Then
        assertNull(model)
    }

    private fun prepareScenario(mediaItemMapper: MediaItemModelMapper) {
        mapper = MediaResultMapperImpl(mediaItemMapper)
    }
}
