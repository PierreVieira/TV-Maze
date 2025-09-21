package org.pierre.tvmaze.feature.episodes.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.pierre.tvmaze.core.room_provider.dao.WatchedEpisodesDao
import org.pierre.tvmaze.core.room_provider.entity.WatchedEpisodeEntity
import org.pierre.tvmaze.dto.episode.EpisodeDto
import org.pierre.tvmaze.feature.episodes.data.mapper.EpisodeWatchedEntityMapper
import org.pierre.tvmaze.feature.episodes.data.mapper.EpisodeWatchedModelMapper
import org.pierre.tvmaze.feature.episodes.data.mapper.WatchedEpisodesIdsMapper
import org.pierre.tvmaze.mapper.EpisodeMapper
import org.pierre.tvmaze.model.common.episode.EpisodeModel
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.model.data_status.toLoadedStatus
import org.pierre.tvmaze.network.data.handler.RequestHandler
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EpisodesRepositoryImplTest {

    private lateinit var repository: EpisodesRepositoryImpl

    @Test
    fun `given success response when getEpisodes then maps and overlays watched`() = runTest {
        // Given
        val episodes = listOf(
            EpisodeDto(id = 1, name = "E1", number = 1, season = 1, image = null),
            EpisodeDto(id = 2, name = "E2", number = 2, season = 1, image = null),
        )
        val jsonBody = Json.encodeToString(episodes)
        val httpClient = HttpClient(MockEngine { request ->
            assertTrue(request.url.encodedPath.contains("/shows/10/episodes"))
            respond(
                content = jsonBody,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
            )
        }) { install(ContentNegotiation) { json() } }
        val handler = RequestHandler(httpClient)

        val fakeDao = FakeWatchedEpisodesDao(watched = mutableListOf(
            WatchedEpisodeEntity(id = 2L, mediaId = 10L, name = null, season = 1, number = 2, originalImageUrl = null, mediumImageUrl = null)
        ))
        val watchedIdsMapper = FakeWatchedEpisodesIdsMapper()

        val mappedModels = listOf(
            EpisodeModel(id = 1L.toLoadedStatus(), mediaId = 10L, name = "E1".toLoadedStatus(), season = 1.toLoadedStatus(), number = 1.toLoadedStatus(), image = null, isWatched = false.toLoadedStatus()),
            EpisodeModel(id = 2L.toLoadedStatus(), mediaId = 10L, name = "E2".toLoadedStatus(), season = 1.toLoadedStatus(), number = 2.toLoadedStatus(), image = null, isWatched = false.toLoadedStatus()),
        )
        val episodeMapper = EpisodeMapper { dto, _, watchedIds ->
            val base = mappedModels.first { it.id.toLoadedData() == dto.id }
            base.copy(isWatched = watchedIds.contains(base.id.toLoadedData()!!).toLoadedStatus())
        }
        val modelMapper = FakeEpisodeWatchedModelMapper()
        val entityMapper = FakeEpisodeWatchedEntityMapper()

        prepareScenario(
            handler,
            episodeMapper,
            fakeDao,
            modelMapper,
            entityMapper,
            watchedIdsMapper,
        )

        // When
        val result = repository.getEpisodes(mediaId = 10L)

        // Then
        assertTrue(result.isSuccess, "Expected success but was $result")
        val list = result.getOrNull()!!
        assertEquals(false, (list[0].isWatched).toLoadedData())
        assertEquals(true, (list[1].isWatched).toLoadedData())
    }

    @Test
    fun `given watched flow emits entities when getWatchedEpisodesFlow then maps to models`() = runTest {
        // Given
        val httpClient = HttpClient(MockEngine { respond("[]", HttpStatusCode.OK, headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())) }) { install(ContentNegotiation) { json() } }
        val handler = RequestHandler(httpClient)
        val state = MutableStateFlow(listOf(
            WatchedEpisodeEntity(id = 5L, mediaId = 22L, name = "Ep" , season = 1, number = 1, originalImageUrl = null, mediumImageUrl = null)
        ))
        val fakeDao = object : WatchedEpisodesDao by FakeWatchedEpisodesDao() {
            override fun getByMediaIdAsFlow(mediaId: Long): Flow<List<WatchedEpisodeEntity>> = state
            override suspend fun getByMediaId(mediaId: Long): List<WatchedEpisodeEntity> = state.value
        }
        val episodeMapper = EpisodeMapper { _, _, _ -> null }
        val modelMapper = FakeEpisodeWatchedModelMapper()
        prepareScenario(handler, episodeMapper, fakeDao, modelMapper, FakeEpisodeWatchedEntityMapper(returnNull = true), FakeWatchedEpisodesIdsMapper())

        // When
        val first = repository.getWatchedEpisodesFlow(mediaId = 22L).first()

        // Then
        assertEquals(1, first.size)
        assertEquals(5L, first[0].id.toLoadedData())
        assertTrue((first[0].isWatched).toLoadedData() == true)

        // And when updates
        state.update { emptyList() }
        val second = repository.getWatchedEpisodesFlow(mediaId = 22L).first()
        assertTrue(second.isEmpty())
    }

    @Test
    fun `given entity mapper returns entity when upsertWatchedEpisode then dao upsert called`() = runTest {
        // Given
        val handler = RequestHandler(HttpClient(MockEngine { respond("[]", HttpStatusCode.OK, headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())) }) { install(ContentNegotiation) { json() } })
        val dao = FakeWatchedEpisodesDao()
        var upsertCalledWith: WatchedEpisodeEntity? = null
        val entityMapper = object : EpisodeWatchedEntityMapper {
            override fun mapOrNull(model: EpisodeModel): WatchedEpisodeEntity? {
                val id = model.id.toLoadedData()!!
                val entity = WatchedEpisodeEntity(id, model.mediaId, model.name?.toLoadedData(), model.season?.toLoadedData(), model.number?.toLoadedData(), null, null)
                upsertCalledWith = entity
                return entity
            }
        }
        prepareScenario(
            handler = handler,
            episodeMapper = { _, _, _ -> null },
            watchedEpisodesDao = dao,
            episodeWatchedModelMapper = FakeEpisodeWatchedModelMapper(),
            episodeWatchedEntityMapper = entityMapper,
            watchedEpisodesIdsMapper = FakeWatchedEpisodesIdsMapper()
        )

        // When
        val model = EpisodeModel(1L.toLoadedStatus(), 99L, "N".toLoadedStatus(), 1.toLoadedStatus(), 1.toLoadedStatus(), null, false.toLoadedStatus())
        repository.upsertWatchedEpisode(model)

        // Then
        assertEquals(1L, upsertCalledWith?.id)
        assertEquals(1, dao.upserts)
    }

    @Test
    fun `given entity mapper returns null when upsertWatchedEpisode then dao upsert not called`() = runTest {
        // Given
        val handler = RequestHandler(HttpClient(MockEngine { respond("[]", HttpStatusCode.OK, headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())) }) { install(ContentNegotiation) { json() } })
        val dao = FakeWatchedEpisodesDao()
        prepareScenario(handler,
            { _, _, _ -> null }, dao, FakeEpisodeWatchedModelMapper(), FakeEpisodeWatchedEntityMapper(returnNull = true), FakeWatchedEpisodesIdsMapper())

        // When
        val model = EpisodeModel(id = 0L.toLoadedStatus(), mediaId = 1L, name = null, season = null, number = null, image = null, isWatched = false.toLoadedStatus())
        repository.upsertWatchedEpisode(model)

        // Then
        assertEquals(0, dao.upserts)
    }

    @Test
    fun `when deleteWatchedEpisodeById then dao deleteById called`() = runTest {
        // Given
        val handler = RequestHandler(HttpClient(MockEngine { respond("[]", HttpStatusCode.OK, headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())) }) { install(ContentNegotiation) { json() } })
        val dao = FakeWatchedEpisodesDao()
        prepareScenario(handler,
            { _, _, _ -> null }, dao, FakeEpisodeWatchedModelMapper(), FakeEpisodeWatchedEntityMapper(returnNull = true), FakeWatchedEpisodesIdsMapper())

        // When
        repository.deleteWatchedEpisodeById(123L)

        // Then
        assertEquals(1, dao.deletes)
    }

    private fun prepareScenario(
        handler: RequestHandler,
        episodeMapper: EpisodeMapper,
        watchedEpisodesDao: WatchedEpisodesDao,
        episodeWatchedModelMapper: EpisodeWatchedModelMapper,
        episodeWatchedEntityMapper: EpisodeWatchedEntityMapper,
        watchedEpisodesIdsMapper: WatchedEpisodesIdsMapper,
    ) {
        repository = EpisodesRepositoryImpl(
            requestHandler = handler,
            episodeMapper = episodeMapper,
            watchedEpisodesDao = watchedEpisodesDao,
            episodeWatchedModelMapper = episodeWatchedModelMapper,
            episodeWatchedEntityMapper = episodeWatchedEntityMapper,
            watchedEpisodesIdsMapper = watchedEpisodesIdsMapper,
        )
    }
}

private class FakeWatchedEpisodesDao(
    watched: MutableList<WatchedEpisodeEntity> = mutableListOf()
) : WatchedEpisodesDao {
    private val flow = MutableStateFlow<List<WatchedEpisodeEntity>>(watched)
    var upserts: Int = 0
        private set
    var deletes: Int = 0
        private set

    override fun getByMediaIdAsFlow(mediaId: Long): Flow<List<WatchedEpisodeEntity>> = flow

    override suspend fun getByMediaId(mediaId: Long): List<WatchedEpisodeEntity> = flow.value

    override suspend fun upsert(entity: WatchedEpisodeEntity) {
        upserts++
        val current = flow.value.toMutableList()
        val idx = current.indexOfFirst { it.id == entity.id }
        if (idx >= 0) current[idx] = entity else current.add(entity)
        flow.value = current
    }

    override suspend fun deleteById(id: Long) {
        deletes++
        flow.update { list -> list.filterNot { it.id == id } }
    }
}

private class FakeWatchedEpisodesIdsMapper : WatchedEpisodesIdsMapper {
    override fun map(entities: List<WatchedEpisodeEntity>): Set<Long> = entities.map { it.id }.toSet()
}

private class FakeEpisodeWatchedModelMapper : EpisodeWatchedModelMapper {
    override fun map(entity: WatchedEpisodeEntity): EpisodeModel = EpisodeModel(
        id = entity.id.toLoadedStatus(),
        mediaId = entity.mediaId,
        name = entity.name?.toLoadedStatus(),
        season = entity.season?.toLoadedStatus(),
        number = entity.number?.toLoadedStatus(),
        image = null,
        isWatched = true.toLoadedStatus(),
    )
}

private class FakeEpisodeWatchedEntityMapper(private val returnNull: Boolean = false) : EpisodeWatchedEntityMapper {
    override fun mapOrNull(model: EpisodeModel): WatchedEpisodeEntity? = if (returnNull) null else WatchedEpisodeEntity(
        id = model.id.toLoadedData()!!,
        mediaId = model.mediaId,
        name = model.name?.toLoadedData(),
        season = model.season?.toLoadedData(),
        number = model.number?.toLoadedData(),
        originalImageUrl = null,
        mediumImageUrl = null,
    )
}
