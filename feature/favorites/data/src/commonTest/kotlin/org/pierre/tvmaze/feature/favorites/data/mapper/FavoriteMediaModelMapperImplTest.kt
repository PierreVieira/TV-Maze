package org.pierre.tvmaze.feature.favorites.data.mapper

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.pierre.tvmaze.core.room_provider.entity.FavoriteMediaEntity
import org.pierre.tvmaze.model.common.image.ImagesModel
import org.pierre.tvmaze.model.common.media.MediaItemDatesModel
import org.pierre.tvmaze.model.common.media.MediaItemModel
import org.pierre.tvmaze.model.common.media.StarsModel
import org.pierre.tvmaze.model.data_status.DataStatus

class FavoriteMediaModelMapperImplTest {

    private lateinit var mapper: FavoriteMediaModelMapper

    @Test
    fun `given entity with full data when map then returns full media item model`() {
        prepareScenario()
        // Given
        val entity = FavoriteMediaEntity(
            id = 7L,
            name = "The Show",
            originalImageUrl = "o.jpg",
            mediumImageUrl = "m.jpg",
            startYear = 2011,
            endYear = 2015,
            averageRating = 9.1,
            fullStarsAmount = 4,
            showStarInAHalf = false,
        )

        // When
        val model: MediaItemModel = mapper.map(entity)

        // Then
        val expected = MediaItemModel(
            id = DataStatus.Loaded(7L),
            name = DataStatus.Loaded("The Show"),
            images = DataStatus.Loaded(ImagesModel(medium = "m.jpg", original = "o.jpg")),
            dates = DataStatus.Loaded(MediaItemDatesModel.StartAndEnd(startYear = 2011, endYear = 2015)),
            stars = DataStatus.Loaded(StarsModel(fullStarsAmount = 4, showInAHalf = false)),
            isFavorite = DataStatus.Loaded(true),
            averageRanting = DataStatus.Loaded(9.1),
            summary = null,
            genres = null,
        )
        assertEquals(expected, model)
    }

    @Test
    fun `given entity with only start year when map then dates is Running and stars null if not both provided`() {
        prepareScenario()
        // Given
        val entity = FavoriteMediaEntity(
            id = 3L,
            name = "A",
            originalImageUrl = null,
            mediumImageUrl = null,
            startYear = 2020,
            endYear = null,
            averageRating = null,
            fullStarsAmount = null,
            showStarInAHalf = null,
        )

        // When
        val model = mapper.map(entity)

        // Then
        assertEquals(DataStatus.Loaded(MediaItemDatesModel.Running(2020)), model.dates)
        assertNull(model.stars)
    }

    private fun prepareScenario() {
        mapper = FavoriteMediaModelMapperImpl()
    }
}
