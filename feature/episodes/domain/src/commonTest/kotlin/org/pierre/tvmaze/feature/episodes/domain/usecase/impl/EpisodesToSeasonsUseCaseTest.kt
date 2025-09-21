package org.pierre.tvmaze.feature.episodes.domain.usecase.impl

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class EpisodesToSeasonsUseCaseTest {

    private val useCase = EpisodesToSeasonsUseCase()

    @Test
    fun `given episodes across seasons when map then groups by season and sorts by number`() {
        // Given
        val episodes = listOf(
            episode(id = 3, season = 2, number = 2),
            episode(id = 1, season = 1, number = 2),
            episode(id = 2, season = 1, number = 1),
            episode(id = 4, season = 2, number = null), // unknown last
        )

        // When
        val seasons = useCase.map(episodes)

        // Then
        val expected = listOf(
            SeasonModel(
                mediaId = 10L,
                number = 1.toLoadedStatus(),
                episodes = listOf(
                    episode(id = 2, season = 1, number = 1),
                    episode(id = 1, season = 1, number = 2),
                ),
                isCollapsed = true,
            ),
            SeasonModel(
                mediaId = 10L,
                number = 2.toLoadedStatus(),
                episodes = listOf(
                    episode(id = 3, season = 2, number = 2),
                    episode(id = 4, season = 2, number = null),
                ),
                isCollapsed = true,
            ),
        )
        assertEquals(expected, seasons)
    }

    @Test
    fun `given empty list when map then returns empty`() {
        // When
        val seasons = useCase.map(emptyList())
        // Then
        assertEquals(emptyList(), seasons)
    }

    // Helpers
    private fun episode(id: Long, season: Int, number: Int?): EpisodeModel = EpisodeModel(
        id = id.toLoadedStatus(),
        mediaId = 10L,
        name = null,
        season = season.toLoadedStatus(),
        number = number?.toLoadedStatus(),
        image = null,
        isWatched = false.toLoadedStatus(),
    )
}
