package org.pierre.tvmaze.mapper.impl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.pierre.tvmaze.dto.image.ImageDto
import org.pierre.tvmaze.dto.media.MediaDto
import org.pierre.tvmaze.dto.media.RantingDto
import org.pierre.tvmaze.mapper.HtmlTextCleaner
import org.pierre.tvmaze.mapper.MediaItemDatesMapper
import org.pierre.tvmaze.mapper.MediaModelMapper
import org.pierre.tvmaze.mapper.StarsMapper
import org.pierre.tvmaze.model.common.image.ImagesModel
import org.pierre.tvmaze.model.common.media.MediaItemDatesModel
import org.pierre.tvmaze.model.common.media.MediaItemModel
import org.pierre.tvmaze.model.common.media.StarsModel
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class MediaItemModelMapperImplTest {

    private lateinit var mapper: MediaItemModelMapperImpl

    @Test
    fun `given media dto with all fields when map then returns full media item model`() {
        prepareScenario()
        // Given
        val dto = MediaDto(
            id = 42L,
            name = "Some Show",
            image = ImageDto(
                medium = "m.jpg",
                original = "o.jpg",
            ),
            type = "Scripted",
            genres = listOf("Drama", "Sci-Fi"),
            status = "Ended",
            premiered = "2010-09-22",
            ended = "2016-05-23",
            rating = RantingDto(average = 8.5),
            summary = "<p>An <b>amazing</b> show</p>",
        )

        // When
        val model = mapper.map(dto)

        // Then
        val expected = MediaItemModel(
            id = 42L.toLoadedStatus(),
            name = "Some Show".toLoadedStatus(),
            images = ImagesModel(medium = "m.jpg", original = "o.jpg").toLoadedStatus(),
            stars = StarsModel(fullStarsAmount = 4, showInAHalf = false).toLoadedStatus(),
            isFavorite = false.toLoadedStatus(),
            averageRanting = 8.5.toLoadedStatus(),
            dates = MediaItemDatesModel.StartAndEnd(2010, 2016).toLoadedStatus(),
            summary = "An amazing show".toLoadedStatus(),
            genres = DataStatus.Loaded(listOf("Drama", "Sci-Fi"))
        )
        assertEquals(expected, model)
    }

    @Test
    fun `given media dto without id when map then returns null`() {
        prepareScenario()
        // Given
        val dto = MediaDto(
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

        // When
        val model = mapper.map(dto)

        // Then
        assertNull(model)
    }

    private fun prepareScenario(
        starsMapper: StarsMapper = StarsMapper { StarsModel(4, false) },
        datesMapper: MediaItemDatesMapper = MediaItemDatesMapper { _, premiered, ended ->
            if (premiered == "2010-09-22" && ended == "2016-05-23") MediaItemDatesModel.StartAndEnd(2010, 2016) else null
        },
        htmlTextCleaner: HtmlTextCleaner = HtmlTextCleaner { it.replace("<[^>]+>".toRegex(), " ").replace("\\s{2,}".toRegex(), " ").trim() },
        mediaModelMapper: MediaModelMapper = MediaModelMapper { dto -> ImagesModel(dto.medium, dto.original) },
    ) {
        mapper = MediaItemModelMapperImpl(
            starsMapper = starsMapper,
            datesMapper = datesMapper,
            htmlTextCleaner = htmlTextCleaner,
            mediaModelMapper = mediaModelMapper,
        )
    }
}
