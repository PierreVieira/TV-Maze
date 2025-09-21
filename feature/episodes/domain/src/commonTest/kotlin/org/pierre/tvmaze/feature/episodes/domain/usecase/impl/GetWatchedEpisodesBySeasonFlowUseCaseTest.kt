package org.pierre.tvmaze.feature.episodes.domain.usecase.impl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.feature.episodes.domain.repository.EpisodesRepository
import org.pierre.tvmaze.feature.episodes.domain.usecase.EpisodesToSeason
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class GetWatchedEpisodesBySeasonFlowUseCaseTest {

    private lateinit var useCase: GetWatchedEpisodesBySeasonFlowUseCase

    @Test
    fun `given repository emits episodes when invoke then maps to seasons`() = runTest {
        // Given
        val episodesFlow = MutableSharedFlow<List<EpisodeModel>>(replay = 1)
        val repo = object : EpisodesRepository {
            override suspend fun getEpisodes(mediaId: Long) = error("not used")
            override fun getWatchedEpisodesFlow(mediaId: Long) = episodesFlow
            override suspend fun upsertWatchedEpisode(episode: EpisodeModel) = Unit
            override suspend fun deleteWatchedEpisodeById(id: Long) = Unit
        }
        val mapped = listOf(
            SeasonModel(
                mediaId = 7L,
                number = 1.toLoadedStatus(),
                episodes = emptyList(),
                isCollapsed = true
            )
        )
        val episodesToSeason = EpisodesToSeason { mapped }
        useCase = GetWatchedEpisodesBySeasonFlowUseCase(repo, episodesToSeason)

        // When
        val job = backgroundScope
        episodesFlow.emit(emptyList())

        // Then
        val first = useCase.invoke(7L).first()
        assertEquals(mapped, first)
    }
}
