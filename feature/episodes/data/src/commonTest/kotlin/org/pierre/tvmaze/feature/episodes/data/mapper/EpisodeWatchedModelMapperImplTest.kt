package org.pierre.tvmaze.feature.episodes.data.mapper

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pierre.tvmaze.core.room_provider.entity.WatchedEpisodeEntity
import org.pierre.tvmaze.feature.episodes.data.mapper.impl.EpisodeWatchedModelMapperImpl
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.common.image.ImagesModel
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class EpisodeWatchedModelMapperImplTest {

    private lateinit var mapper: EpisodeWatchedModelMapper

    @Test
    fun `given watched episode entity when map then returns episode model with isWatched true`() {
        prepareScenario()
        // Given
        val entity = WatchedEpisodeEntity(
            id = 55L,
            mediaId = 10L,
            name = "Finale",
            season = 3,
            number = 10,
            originalImageUrl = "o.jpg",
            mediumImageUrl = "m.jpg",
        )

        // When
        val model: EpisodeModel = mapper.map(entity)

        // Then
        val expected = EpisodeModel(
            id = 55L.toLoadedStatus(),
            mediaId = 10L,
            name = "Finale".toLoadedStatus(),
            season = 3.toLoadedStatus(),
            number = 10.toLoadedStatus(),
            image = ImagesModel(medium = "m.jpg", original = "o.jpg").toLoadedStatus(),
            isWatched = true.toLoadedStatus(),
        )
        assertEquals(expected, model)
        // Additional check that statuses are Loaded
        assertEquals(55L, model.id.toLoadedData())
        assertEquals(true, model.isWatched.toLoadedData())
    }

    private fun prepareScenario() {
        mapper = EpisodeWatchedModelMapperImpl()
    }
}
