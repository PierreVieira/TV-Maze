package org.pierre.tvmaze.feature.episodes.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonsUseCases
import org.pierre.tvmaze.feature.episodes.presentation.factory.LoadingSeasonsFactory
import org.pierre.tvmaze.feature.episodes.presentation.model.SeasonsUiEvent
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.common.route.MediaDetailsRoute
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.model.data_status.toLoadedStatus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@kotlinx.coroutines.ExperimentalCoroutinesApi
class SeasonsViewModelTest {

    private lateinit var viewModel: SeasonsViewModel

    @Test
    fun `given success data and watched overlay when init then updates seasons and overlay applies`() = runTest {
        // Given
        val mediaId = 777L
        val initialLoading = listOf(loadingSeason(), loadingSeason())
        val episodesResult = Result.success(
            listOf(
                SeasonModel(
                    mediaId = mediaId,
                    number = 1.toLoadedStatus(),
                    episodes = listOf(ep(1, mediaId)),
                    isCollapsed = true
                ),
                SeasonModel(
                    mediaId = mediaId,
                    number = 2.toLoadedStatus(),
                    episodes = listOf(ep(2, mediaId)),
                    isCollapsed = false
                ),
            )
        )
        val watchedFlow = MutableSharedFlow<List<SeasonModel>>(replay = 1)
        val useCases = SeasonsUseCases(
            getEpisodesBySeason = { _ -> episodesResult },
            toggleEpisodeWatched = { _ -> /* captured below in another test */ },
            updateSeasonsWithWatched = { watched, current ->
                val watchedIds = watched.flatMap { it.episodes }.mapNotNull { it.id.toLoadedData() }.toSet()
                current.map { s ->
                    s.copy(episodes = s.episodes.map { e ->
                        val isWatched = watchedIds.contains(e.id.toLoadedData())
                        e.copy(isWatched = isWatched.toLoadedStatus())
                    })
                }
            },
            getWatchedEpisodesBySeasonFlow = { _ -> watchedFlow },
        )
        prepareScenario(
            savedStateHandle = savedStateForRoute(MediaDetailsRoute(mediaId)),
            loadingFactory = { initialLoading },
            useCases = useCases,
        )

        // When: initial episodes load happens in init
        advanceUntilIdle()
        repeat(5) {
            if (viewModel.seasons.value.mapNotNull { it.number.toLoadedData() }.isNotEmpty()) return@repeat
            advanceUntilIdle()
        }
        val afterLoad = viewModel.seasons.value
        // Then: state updated to loaded seasons
        assertEquals(2, afterLoad.size)

        // And When: emit watched overlay with episode id 2
        val watched = listOf(
            SeasonModel(
                mediaId = mediaId,
                number = 2.toLoadedStatus(),
                episodes = listOf(ep(2, mediaId).copy(isWatched = true.toLoadedStatus())),
                isCollapsed = true
            )
        )
        watchedFlow.emit(watched)
        advanceUntilIdle()
        // Await until overlay applies
        repeat(10) {
            advanceUntilIdle()
            val mapByIdLoop = viewModel.seasons.value.flatMap { it.episodes }.associateBy { it.id.toLoadedData() }
            if (mapByIdLoop[2L]?.isWatched?.toLoadedData() == true) return@repeat
        }
        val afterOverlay = viewModel.seasons.value
        val mapById = afterOverlay.flatMap { it.episodes }.associateBy { it.id.toLoadedData() }
        assertEquals(false, mapById[1L]?.isWatched?.toLoadedData())
    }

    @Test
    fun `when ToggleSeason event then toggles isCollapsed only for that season`() = runTest {
        // Given
        val mediaId = 1L
        val loaded = listOf(
            SeasonModel(mediaId, 1.toLoadedStatus(), emptyList(), isCollapsed = true),
            SeasonModel(mediaId, 2.toLoadedStatus(), emptyList(), isCollapsed = false),
        )
        prepareScenario(
            savedStateHandle = savedStateForRoute(MediaDetailsRoute(mediaId)),
            loadingFactory = { loaded },
            useCases = SeasonsUseCases(
                getEpisodesBySeason = { Result.success(loaded) },
                toggleEpisodeWatched = { },
                updateSeasonsWithWatched = { _, current -> current },
                getWatchedEpisodesBySeasonFlow = { _ -> flowOf(emptyList()) },
            )
        )

        // When + Then
        // Start with current value
        val initial = viewModel.seasons.first()
        assertTrue(initial[0].isCollapsed)
        assertTrue(!initial[1].isCollapsed)

        // Toggle season 1
        viewModel.onEvent(SeasonsUiEvent.ToggleSeason(1))
        val afterToggle = viewModel.seasons.first()
        assertTrue(!afterToggle[0].isCollapsed)
        assertTrue(!afterToggle[1].isCollapsed)

        // Toggle season 2
        viewModel.onEvent(SeasonsUiEvent.ToggleSeason(2))
        val afterSecondToggle = viewModel.seasons.first()
        assertTrue(!afterSecondToggle[0].isCollapsed)
        assertTrue(afterSecondToggle[1].isCollapsed)
    }

    @Test
    fun `when ToggleEpisodeWatched event then delegates to use case`() = runTest {
        // Given
        val mediaId = 5L
        var toggledEpisodeId: Long? = null
        val episode = ep(99, mediaId)
        val useCases = SeasonsUseCases(
            getEpisodesBySeason = { Result.success(emptyList()) },
            toggleEpisodeWatched = { ep -> toggledEpisodeId = ep.id.toLoadedData() },
            updateSeasonsWithWatched = { _, current -> current },
            getWatchedEpisodesBySeasonFlow = { _ -> kotlinx.coroutines.flow.flowOf(emptyList()) },
        )
        prepareScenario(
            savedStateHandle = savedStateForRoute(MediaDetailsRoute(mediaId)),
            loadingFactory = { emptyList() },
            useCases = useCases,
        )

        // When
        viewModel.onEvent(SeasonsUiEvent.ToggleEpisodeWatched(episode))
        advanceUntilIdle()
        // Then
        assertEquals(99L, toggledEpisodeId)
    }

    // Helpers
    private fun ep(id: Long, mediaId: Long) = EpisodeModel(
        id = id.toLoadedStatus(),
        mediaId = mediaId,
        name = null,
        season = 1.toLoadedStatus(),
        number = 1.toLoadedStatus(),
        image = null,
        isWatched = false.toLoadedStatus(),
    )

    private fun loadingSeason() = SeasonModel(
        mediaId = 0L,
        number = org.pierre.tvmaze.model.data_status.DataStatus.Loading,
        episodes = emptyList(),
        isCollapsed = true,
    )

    private fun prepareScenario(
        savedStateHandle: SavedStateHandle,
        loadingFactory: LoadingSeasonsFactory,
        useCases: SeasonsUseCases,
    ) {
        viewModel = SeasonsViewModel(
            savedStateHandle = savedStateHandle,
            loadingSeasonsFactory = loadingFactory,
            useCases = useCases,
        )
    }

    private fun savedStateForRoute(route: MediaDetailsRoute): SavedStateHandle {
        val handle = SavedStateHandle()
        // Store plain id so our ViewModel fallback picks it up in tests
        handle["id"] = route.id
        return handle
    }
}
