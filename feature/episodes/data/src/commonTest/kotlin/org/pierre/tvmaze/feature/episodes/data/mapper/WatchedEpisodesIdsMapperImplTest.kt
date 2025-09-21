package org.pierre.tvmaze.feature.episodes.data.mapper

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pierre.tvmaze.core.room_provider.entity.WatchedEpisodeEntity
import org.pierre.tvmaze.feature.episodes.data.mapper.impl.WatchedEpisodesIdsMapperImpl

class WatchedEpisodesIdsMapperImplTest {

    private lateinit var mapper: WatchedEpisodesIdsMapper

    @Test
    fun `given watched episode entities when map then returns set of their ids`() {
        prepareScenario()
        // Given
        val list = listOf(
            WatchedEpisodeEntity(
                id = 1L,
                mediaId = 9L,
                name = null,
                season = 1,
                number = 1,
                originalImageUrl = null,
                mediumImageUrl = null
            ),
            WatchedEpisodeEntity(
                id = 2L,
                mediaId = 9L,
                name = null,
                season = 1,
                number = 2,
                originalImageUrl = null,
                mediumImageUrl = null
            ),
            WatchedEpisodeEntity(
                id = 2L,
                mediaId = 9L,
                name = null,
                season = 1,
                number = 2,
                originalImageUrl = null,
                mediumImageUrl = null
            ),
        )

        // When
        val result: Set<Long> = mapper.map(list)

        // Then
        assertEquals(setOf(1L, 2L), result)
    }

    private fun prepareScenario() {
        mapper = WatchedEpisodesIdsMapperImpl()
    }
}
