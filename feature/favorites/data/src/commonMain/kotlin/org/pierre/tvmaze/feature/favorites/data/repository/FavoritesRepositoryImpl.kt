package org.pierre.tvmaze.feature.favorites.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.pierre.tvmaze.core.room_provider.dao.FavoriteMediasDao
import org.pierre.tvmaze.feature.favorites.data.mapper.FavoriteMediaEntityMapper
import org.pierre.tvmaze.feature.favorites.data.mapper.FavoriteMediaModelMapper
import org.pierre.tvmaze.feature.favorites.domain.repository.FavoritesRepository
import org.pierre.tvmaze.model.common.media.MediaItemModel
import org.pierre.tvmaze.model.data_status.toLoadedData

class FavoritesRepositoryImpl(
    private val dao: FavoriteMediasDao,
    private val favoriteMediaEntityMapper: FavoriteMediaEntityMapper,
    private val favoriteMediaModelMapper: FavoriteMediaModelMapper,
) : FavoritesRepository {

    override suspend fun toggleFavorite(show: MediaItemModel): Result<Unit> {
        val id = show.id.toLoadedData() ?: return failure(ERROR_ID_NOT_LOADED)
        val isFavorite = show.isFavorite.toLoadedData() ?: return failure(ERROR_IS_FAVORITE_NOT_LOADED)
        return if (isFavorite) {
            runCatching { dao.deleteById(id) }
        } else {
            val entity = favoriteMediaEntityMapper.mapOrNull(show)
                ?: return failure(ERROR_SHOW_NOT_FULLY_LOADED)
            runCatching { dao.upsert(entity) }
        }
    }

    override fun getAllFavoritesAsFlow(): Flow<List<MediaItemModel>> =
        dao.getAllAsFlow().map { favoriteShowEntities ->
            favoriteShowEntities.map(favoriteMediaModelMapper::map)
        }

    override suspend fun getAllFavorites(): List<MediaItemModel> = dao.getAll().map(favoriteMediaModelMapper::map)

    private fun failure(message: String): Result<Unit> = Result.failure(IllegalStateException(message))

    private companion object {
        private const val ERROR_ID_NOT_LOADED = "Show id is not loaded"
        private const val ERROR_IS_FAVORITE_NOT_LOADED = "isFavorite is not loaded"
        private const val ERROR_SHOW_NOT_FULLY_LOADED = "Show data is not fully loaded to upsert"
    }
}
