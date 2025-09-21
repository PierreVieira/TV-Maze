package org.pierre.tvmaze.feature.episodes.data.mapper

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.pierre.tvmaze.core.room_provider.entity.WatchedEpisodeEntity
import org.pierre.tvmaze.feature.episodes.data.mapper.impl.EpisodeWatchedEntityMapperImpl
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.common.image.ImagesModel
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class EpisodeWatchedEntityMapperImplTest {

    private lateinit var mapper: EpisodeWatchedEntityMapper

    @Test
    fun `given episode with loaded id when mapOrNull then returns entity with same fields`() {
        prepareScenario()
        // Given
        val model = EpisodeModel(
            id = 10L.toLoadedStatus(),
            mediaId = 7L,
            name = "Pilot".toLoadedStatus(),
            season = 1.toLoadedStatus(),
            number = 1.toLoadedStatus(),
            image = ImagesModel(medium = "m.jpg", original = "o.jpg").toLoadedStatus(),
            isWatched = true.toLoadedStatus(),
        )

        // When
        val entity = mapper.mapOrNull(model)

        // Then
        val expected = WatchedEpisodeEntity(
            id = 10L,
            mediaId = 7L,
            name = "Pilot",
            season = 1,
            number = 1,
            originalImageUrl = "o.jpg",
            mediumImageUrl = "m.jpg",
        )
        assertEquals(expected, entity)
    }

    @Test
    fun `given episode without loaded id when mapOrNull then returns null`() {
        prepareScenario()
        // Given
        val model = EpisodeModel(
            id = DataStatus.Loading,
            mediaId = 1L,
            name = null,
            season = null,
            number = null,
            image = null,
            isWatched = false.toLoadedStatus(),
        )
        // When
        val result = mapper.mapOrNull(model)
        // Then
        assertNull(result)
    }

    private fun prepareScenario() {
        mapper = EpisodeWatchedEntityMapperImpl()
    }
}
