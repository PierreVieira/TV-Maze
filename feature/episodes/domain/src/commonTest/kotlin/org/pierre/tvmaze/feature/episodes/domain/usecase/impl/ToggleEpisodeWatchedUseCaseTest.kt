package org.pierre.tvmaze.feature.episodes.domain.usecase.impl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.pierre.tvmaze.feature.episodes.domain.repository.EpisodesRepository
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.tvmaze.model.data_status.toLoadedStatus

class ToggleEpisodeWatchedUseCaseTest {

    private lateinit var useCase: ToggleEpisodeWatchedUseCase

    private class RepoFake : EpisodesRepository {
        var deletedId: Long? = null
        var upserted: EpisodeModel? = null
        override suspend fun getEpisodes(mediaId: Long) = error("not used")
        override fun getWatchedEpisodesFlow(mediaId: Long) = error("not used")
        override suspend fun upsertWatchedEpisode(episode: EpisodeModel) { upserted = episode }
        override suspend fun deleteWatchedEpisodeById(id: Long) { deletedId = id }
    }

    @Test
    fun `given episode watched when invoke then deletes by id`() = runTest {
        // Given
        val repo = RepoFake()
        useCase = ToggleEpisodeWatchedUseCase(repo)
        val episode = EpisodeModel(
            id = 55L.toLoadedStatus(),
            mediaId = 9L,
            name = null,
            season = null,
            number = null,
            image = null,
            isWatched = true.toLoadedStatus(),
        )

        // When
        useCase.invoke(episode)

        // Then
        assertEquals(55L, repo.deletedId)
        assertTrue(repo.upserted == null)
    }

    @Test
    fun `given episode not watched when invoke then upserts entity`() = runTest {
        // Given
        val repo = RepoFake()
        useCase = ToggleEpisodeWatchedUseCase(repo)
        val episode = EpisodeModel(
            id = 10L.toLoadedStatus(),
            mediaId = 1L,
            name = null,
            season = null,
            number = null,
            image = null,
            isWatched = false.toLoadedStatus(),
        )

        // When
        useCase.invoke(episode)

        // Then
        assertEquals(10L, repo.upserted?.id.let { (it as? DataStatus.Loaded)?.data })
        assertTrue(repo.deletedId == null)
    }

    @Test
    fun `given episode id not loaded when invoke then does nothing`() = runTest {
        // Given
        val repo = RepoFake()
        useCase = ToggleEpisodeWatchedUseCase(repo)
        val episode = EpisodeModel(
            id = DataStatus.Loading,
            mediaId = 1L,
            name = null,
            season = null,
            number = null,
            image = null,
            isWatched = false.toLoadedStatus(),
        )

        // When
        useCase.invoke(episode)

        // Then
        assertTrue(repo.deletedId == null)
        assertFalse(repo.upserted != null)
    }
}
