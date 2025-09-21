package org.pierre.tvmaze.feature.episodes.domain.usecase.impl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.pierre.tvmaze.feature.episodes.domain.repository.EpisodesRepository
import org.pierre.tvmaze.feature.episodes.domain.usecase.EpisodesToSeason
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class GetEpisodesBySeasonUseCaseTest {

    private lateinit var useCase: GetEpisodesBySeasonUseCase

    @Test
    fun `given repository returns episodes when invoke then maps with EpisodesToSeason`() = runTest { 
        prepareScenario(
            repo = object : EpisodesRepository {
                override suspend fun getEpisodes(mediaId: Long) = Result.success(
                    listOf(
                        EpisodeModel(
                            id = 1L.toLoadedStatus(),
                            mediaId = mediaId,
                            name = null,
                            season = 1.toLoadedStatus(),
                            number = 1.toLoadedStatus(),
                            image = null,
                            isWatched = false.toLoadedStatus()
                        )
                    )
                )
                override fun getWatchedEpisodesFlow(mediaId: Long) = throw NotImplementedError()
                override suspend fun upsertWatchedEpisode(episode: EpisodeModel) = Unit
                override suspend fun deleteWatchedEpisodeById(id: Long) = Unit
            },
            episodesToSeason = { episodes ->
                // Return a dummy value to ensure mapping happens
                listOf(
                    org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel(
                        mediaId = episodes.first().mediaId,
                        number = 1.toLoadedStatus(),
                        episodes = episodes,
                        isCollapsed = true,
                    )
                )
            }
        )

        // When
        val result = runCatching { useCase.invoke(10L) }

        // Then
        assertTrue(result.isSuccess)
        val seasons = result.getOrNull()!!.getOrNull()
        assertEquals(1, seasons?.size)
        assertEquals(1, seasons?.first()?.episodes?.size)
    }

    @Test
    fun `given repository failure when invoke then returns same failure`() = runTest { 
        val error = IllegalStateException("boom")
        prepareScenario(
            repo = object : EpisodesRepository {
                override suspend fun getEpisodes(mediaId: Long) = Result.failure<List<EpisodeModel>>(error)
                override fun getWatchedEpisodesFlow(mediaId: Long) = throw NotImplementedError()
                override suspend fun upsertWatchedEpisode(episode: EpisodeModel) = Unit
                override suspend fun deleteWatchedEpisodeById(id: Long) = Unit
            },
            episodesToSeason = { emptyList() }
        )

        // When
        val result = useCase.invoke(99L)

        // Then
        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }

    private fun prepareScenario(
        repo: EpisodesRepository,
        episodesToSeason: EpisodesToSeason,
    ) {
        useCase = GetEpisodesBySeasonUseCase(repo, episodesToSeason)
    }
}
