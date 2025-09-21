package org.pierre.tvmaze.feature.favorites.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.pierre.tvmaze.core.room_provider.dao.FavoriteMediasDao
import org.pierre.tvmaze.core.room_provider.entity.FavoriteMediaEntity
import org.pierre.tvmaze.feature.favorites.data.mapper.FavoriteMediaEntityMapper
import org.pierre.tvmaze.feature.favorites.data.mapper.FavoriteMediaModelMapper
import org.pierre.tvmaze.model.common.media.MediaItemModel
import org.pierre.tvmaze.model.common.media.StarsModel
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.model.data_status.toLoadedStatus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class FavoritesRepositoryImplTest {

    private lateinit var repository: FavoritesRepositoryImpl

    @Test
    fun `given isFavorite true when toggleFavorite then deletes by id`() = runTest {
        // Given
        val dao = FakeFavoriteMediasDao()
        var deletedId: Long? = null
        dao.onDelete = { id -> deletedId = id }
        prepareScenario(
            dao = dao,
            entityMapper = { null },
            modelMapper = { entity ->
                MediaItemModel(
                    id = DataStatus.Loaded(entity.id),
                    name = DataStatus.Loaded(entity.name),
                    images = null, dates = null, stars = null,
                    isFavorite = DataStatus.Loaded(true),
                    averageRanting = null, summary = null, genres = null
                )
            }
        )
        val model = MediaItemModel(
            id = 10L.toLoadedStatus(),
            name = "N".toLoadedStatus(),
            images = null, dates = null, stars = null,
            isFavorite = true.toLoadedStatus(),
            averageRanting = null, summary = null, genres = null,
        )

        // When
        val result = repository.toggleFavorite(model)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(10L, deletedId)
    }

    @Test
    fun `given isFavorite false and mapper returns entity when toggleFavorite then upserts entity`() =
        runTest {
            // Given
            val dao = FakeFavoriteMediasDao()
            var upserted: FavoriteMediaEntity? = null
            dao.onUpsert = { e -> upserted = e }
            val expectedEntity = FavoriteMediaEntity(
                id = 5L, name = "S", originalImageUrl = null, mediumImageUrl = null,
                startYear = null, endYear = null, averageRating = null,
                fullStarsAmount = null, showStarInAHalf = null
            )
            prepareScenario(
                dao = dao,
                entityMapper = { _ -> expectedEntity },
                modelMapper = { entity: FavoriteMediaEntity ->
                    MediaItemModel(
                        id = DataStatus.Loaded(entity.id),
                        name = DataStatus.Loaded(entity.name),
                        images = null, dates = null, stars = null,
                        isFavorite = DataStatus.Loaded(true),
                        averageRanting = null, summary = null, genres = null
                    )
                }
            )
            val model = MediaItemModel(
                id = 5L.toLoadedStatus(),
                name = "S".toLoadedStatus(),
                images = null, dates = null, stars = null,
                isFavorite = false.toLoadedStatus(),
                averageRanting = null, summary = null, genres = null,
            )

            // When
            val result = repository.toggleFavorite(model)

            // Then
            assertTrue(result.isSuccess)
            assertEquals(expectedEntity, upserted)
        }

    @Test
    fun `given id not loaded when toggleFavorite then returns failure`() = runTest {
        // Given
        prepareScenario(
            dao = FakeFavoriteMediasDao(),
            entityMapper = { null },
            modelMapper = { entity -> dummyModel(entity) }
        )
        val model = MediaItemModel(
            id = DataStatus.Loading,
            name = "X".toLoadedStatus(),
            images = null, dates = null, stars = null,
            isFavorite = true.toLoadedStatus(),
            averageRanting = null, summary = null, genres = null,
        )

        // When
        val result = repository.toggleFavorite(model)

        // Then
        assertTrue(result.isFailure)
        assertIs<IllegalStateException>(result.exceptionOrNull())
        assertTrue(
            result.exceptionOrNull()!!.message!!.contains(
                "id is not loaded",
                ignoreCase = true
            )
        )
    }

    @Test
    fun `given isFavorite not loaded when toggleFavorite then returns failure`() = runTest {
        // Given
        prepareScenario(
            dao = FakeFavoriteMediasDao(),
            entityMapper = { null },
            modelMapper = { entity -> dummyModel(entity) }
        )
        val model = MediaItemModel(
            id = 1L.toLoadedStatus(),
            name = "X".toLoadedStatus(),
            images = null, dates = null, stars = null,
            isFavorite = DataStatus.Loading,
            averageRanting = null, summary = null, genres = null,
        )

        // When
        val result = repository.toggleFavorite(model)

        // Then
        assertTrue(result.isFailure)
        assertIs<IllegalStateException>(result.exceptionOrNull())
        assertTrue(
            result.exceptionOrNull()!!.message!!.contains(
                "isFavorite is not loaded",
                ignoreCase = true
            )
        )
    }

    @Test
    fun `given mapper returns null when toggleFavorite then returns failure`() = runTest {
        // Given
        prepareScenario(
            dao = FakeFavoriteMediasDao(),
            entityMapper = { null },
            modelMapper = { entity -> dummyModel(entity) }
        )
        val model = MediaItemModel(
            id = 2L.toLoadedStatus(),
            name = "X".toLoadedStatus(),
            images = null, dates = null, stars = null,
            isFavorite = false.toLoadedStatus(),
            averageRanting = null, summary = null, genres = null,
        )

        // When
        val result = repository.toggleFavorite(model)

        // Then
        assertTrue(result.isFailure)
        val ex = result.exceptionOrNull()
        assertIs<IllegalStateException>(ex)
        assertNotNull(ex)
        assertTrue(ex.message!!.contains("fully loaded", ignoreCase = true))
    }

    @Test
    fun `given dao flow emits entities when getAllFavoritesAsFlow then maps to models`() = runTest {
        // Given
        val state = MutableStateFlow(
            listOf(
                FavoriteMediaEntity(1L, "A", null, null, null, null, null, null, null)
            )
        )
        val dao = object : FavoriteMediasDao by FakeFavoriteMediasDao(state) {
            override fun getAllAsFlow(): Flow<List<FavoriteMediaEntity>> = state
            override suspend fun getAll(): List<FavoriteMediaEntity> = state.value
        }
        var mappedCount = 0
        val modelMapper = FavoriteMediaModelMapper { entity: FavoriteMediaEntity ->
            mappedCount++
            dummyModel(entity)
        }
        prepareScenario(
            dao,
            entityMapper = { null },
            modelMapper = modelMapper
        )

        // When
        val first = repository.getAllFavoritesAsFlow().first()

        // Then
        assertEquals(1, first.size)
        assertEquals(1L, first[0].id.toLoadedData())
        assertEquals(1, mappedCount)

        // And When update
        state.value = emptyList()
        val second = repository.getAllFavoritesAsFlow().first()
        assertTrue(second.isEmpty())
    }

    @Test
    fun `when getAllFavorites then returns mapped list`() = runTest {
        // Given
        val entities = listOf(
            FavoriteMediaEntity(1L, "A", null, null, null, null, null, null, null),
            FavoriteMediaEntity(2L, "B", null, null, null, null, null, null, null),
        )
        val dao = FakeFavoriteMediasDao(MutableStateFlow(entities))
        val modelMapper = FavoriteMediaModelMapper { entity: FavoriteMediaEntity -> dummyModel(entity) }
        prepareScenario(
            dao,
            entityMapper = { null },
            modelMapper = modelMapper
        )

        // When
        val list = repository.getAllFavorites()

        // Then
        assertEquals(listOf(1L, 2L), list.map { it.id.toLoadedData() })
    }

    private fun prepareScenario(
        dao: FavoriteMediasDao,
        entityMapper: FavoriteMediaEntityMapper,
        modelMapper: FavoriteMediaModelMapper,
    ) {
        repository = FavoritesRepositoryImpl(
            dao = dao,
            favoriteMediaEntityMapper = entityMapper,
            favoriteMediaModelMapper = modelMapper,
        )
    }
}

private class FakeFavoriteMediasDao(
    private val state: MutableStateFlow<List<FavoriteMediaEntity>> = MutableStateFlow(emptyList()),
) : FavoriteMediasDao {
    var onUpsert: ((FavoriteMediaEntity) -> Unit)? = null
    var onDelete: ((Long) -> Unit)? = null

    override fun getAllAsFlow(): Flow<List<FavoriteMediaEntity>> = state

    override suspend fun getAll(): List<FavoriteMediaEntity> = state.value

    override suspend fun getById(id: Long): FavoriteMediaEntity? =
        state.value.firstOrNull { it.id == id }

    override suspend fun upsert(entity: FavoriteMediaEntity) {
        onUpsert?.invoke(entity)
        val current = state.value.toMutableList()
        val idx = current.indexOfFirst { it.id == entity.id }
        if (idx >= 0) current[idx] = entity else current.add(entity)
        state.value = current
    }

    override suspend fun update(entity: FavoriteMediaEntity) {
        upsert(entity)
    }

    override suspend fun deleteById(id: Long) {
        onDelete?.invoke(id)
        state.value = state.value.filterNot { it.id == id }
    }

    override suspend fun clear() {
        state.value = emptyList()
    }
}

private fun dummyModel(entity: FavoriteMediaEntity): MediaItemModel = MediaItemModel(
    id = DataStatus.Loaded(entity.id),
    name = DataStatus.Loaded(entity.name),
    images = null,
    dates = null,
    stars = DataStatus.Loaded(StarsModel(0, false)),
    isFavorite = DataStatus.Loaded(true),
    averageRanting = null,
    summary = null,
    genres = null,
)
