package org.pierre.tvmaze.feature.episodes.domain.usecase.impl

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class UpdateSeasonsWithWatchedUseCaseTest {

    private val useCase = UpdateSeasonsWithWatchedUseCase()

    @Test
    fun `given watched list when invoke then overlays isWatched on current seasons`() {
        // Given current seasons with not watched
        val current = listOf(
            SeasonModel(
                mediaId = 1L,
                number = 1.toLoadedStatus(),
                episodes = listOf(
                    ep(1, 1), ep(2, 1)
                ),
                isCollapsed = false,
            ),
            SeasonModel(
                mediaId = 1L,
                number = 2.toLoadedStatus(),
                episodes = listOf(
                    ep(3, 2)
                ),
                isCollapsed = true,
            )
        )
        // Watched contains ep 2 and 3
        val watched = listOf(
            SeasonModel(
                mediaId = 1L,
                number = 1.toLoadedStatus(),
                episodes = listOf(ep(2, 1).copy(isWatched = true.toLoadedStatus())),
                isCollapsed = true,
            ),
            SeasonModel(
                mediaId = 1L,
                number = 2.toLoadedStatus(),
                episodes = listOf(ep(3, 2).copy(isWatched = true.toLoadedStatus())),
                isCollapsed = true,
            )
        )

        // When
        val result = useCase.invoke(watched, current)

        // Then
        val expected = listOf(
            SeasonModel(
                mediaId = 1L,
                number = 1.toLoadedStatus(),
                episodes = listOf(
                    ep(1, 1).copy(isWatched = false.toLoadedStatus()),
                    ep(2, 1).copy(isWatched = true.toLoadedStatus()),
                ),
                isCollapsed = false,
            ),
            SeasonModel(
                mediaId = 1L,
                number = 2.toLoadedStatus(),
                episodes = listOf(
                    ep(3, 2).copy(isWatched = true.toLoadedStatus()),
                ),
                isCollapsed = true,
            )
        )
        assertEquals(expected, result)
    }

    private fun ep(id: Long, season: Int) = EpisodeModel(
        id = id.toLoadedStatus(),
        mediaId = 1L,
        name = null,
        season = season.toLoadedStatus(),
        number = null,
        image = null,
        isWatched = false.toLoadedStatus(),
    )
}
