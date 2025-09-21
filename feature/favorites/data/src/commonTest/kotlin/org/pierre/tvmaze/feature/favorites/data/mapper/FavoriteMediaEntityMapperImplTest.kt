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
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class FavoriteMediaEntityMapperImplTest {

    private lateinit var mapper: FavoriteMediaEntityMapper

    @Test
    fun `given fully loaded media item when mapOrNull then returns FavoriteMediaEntity with same data`() {
        prepareScenario()
        // Given
        val model = MediaItemModel(
            id = 42L.toLoadedStatus(),
            name = "Show".toLoadedStatus(),
            images = ImagesModel(medium = "m.jpg", original = "o.jpg").toLoadedStatus(),
            dates = MediaItemDatesModel.StartAndEnd(startYear = 2010, endYear = 2016).toLoadedStatus(),
            stars = StarsModel(fullStarsAmount = 4, showInAHalf = true).toLoadedStatus(),
            isFavorite = true.toLoadedStatus(),
            averageRanting = 8.7.toLoadedStatus(),
            summary = null,
            genres = null,
        )

        // When
        val entity: FavoriteMediaEntity? = mapper.mapOrNull(model)

        // Then
        val expected = FavoriteMediaEntity(
            id = 42L,
            name = "Show",
            originalImageUrl = "o.jpg",
            mediumImageUrl = "m.jpg",
            startYear = 2010,
            endYear = 2016,
            averageRating = 8.7,
            fullStarsAmount = 4,
            showStarInAHalf = true,
        )
        assertEquals(expected, entity)
    }

    @Test
    fun `given running dates only when mapOrNull then endYear is null`() {
        prepareScenario()
        // Given
        val model = MediaItemModel(
            id = 1L.toLoadedStatus(),
            name = "Title".toLoadedStatus(),
            images = ImagesModel(medium = null, original = null).toLoadedStatus(),
            dates = MediaItemDatesModel.Running(startYear = 2020).toLoadedStatus(),
            stars = null,
            isFavorite = true.toLoadedStatus(),
            averageRanting = null,
            summary = null,
            genres = null,
        )

        // When
        val entity = mapper.mapOrNull(model)!!

        // Then
        assertEquals(2020, entity.startYear)
        assertNull(entity.endYear)
        assertNull(entity.fullStarsAmount)
        assertNull(entity.showStarInAHalf)
    }

    @Test
    fun `given id not loaded when mapOrNull then returns null`() {
        prepareScenario()
        // Given
        val model = MediaItemModel(
            id = DataStatus.Loading,
            name = "Title".toLoadedStatus(),
            images = null,
            dates = null,
            stars = null,
            isFavorite = true.toLoadedStatus(),
            averageRanting = null,
            summary = null,
            genres = null,
        )

        // When
        val result = mapper.mapOrNull(model)

        // Then
        assertNull(result)
    }

    @Test
    fun `given name not loaded when mapOrNull then returns null`() {
        prepareScenario()
        // Given
        val model = MediaItemModel(
            id = 1L.toLoadedStatus(),
            name = DataStatus.Loading,
            images = null,
            dates = null,
            stars = null,
            isFavorite = true.toLoadedStatus(),
            averageRanting = null,
            summary = null,
            genres = null,
        )

        // When
        val result = mapper.mapOrNull(model)

        // Then
        assertNull(result)
    }

    private fun prepareScenario() {
        mapper = FavoriteMediaEntityMapperImpl()
    }
}
