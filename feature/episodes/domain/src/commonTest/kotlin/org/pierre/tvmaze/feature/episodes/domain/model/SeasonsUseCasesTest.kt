package org.pierre.tvmaze.feature.episodes.domain.model

import kotlin.test.Test
import kotlin.test.assertSame
import org.pierre.tvmaze.feature.episodes.domain.usecase.GetEpisodesBySeason
import org.pierre.tvmaze.feature.episodes.domain.usecase.GetWatchedEpisodesBySeasonFlow
import org.pierre.tvmaze.feature.episodes.domain.usecase.ToggleEpisodeWatched
import org.pierre.tvmaze.feature.episodes.domain.usecase.UpdateSeasonsWithWatched

class SeasonsUseCasesTest {

    @Test
    fun `given use cases when construct SeasonsUseCases then fields are the same instances`() {
        // Given
        val getEpisodes = GetEpisodesBySeason { error("not used") }
        val toggle = ToggleEpisodeWatched { _ -> }
        val update = UpdateSeasonsWithWatched { _, _ -> emptyList() }
        val watchedFlow = GetWatchedEpisodesBySeasonFlow { _ -> kotlinx.coroutines.flow.emptyFlow() }

        // When
        val container = SeasonsUseCases(
            getEpisodesBySeason = getEpisodes,
            toggleEpisodeWatched = toggle,
            updateSeasonsWithWatched = update,
            getWatchedEpisodesBySeasonFlow = watchedFlow,
        )

        // Then
        assertSame(getEpisodes, container.getEpisodesBySeason)
        assertSame(toggle, container.toggleEpisodeWatched)
        assertSame(update, container.updateSeasonsWithWatched)
        assertSame(watchedFlow, container.getWatchedEpisodesBySeasonFlow)
    }
}
