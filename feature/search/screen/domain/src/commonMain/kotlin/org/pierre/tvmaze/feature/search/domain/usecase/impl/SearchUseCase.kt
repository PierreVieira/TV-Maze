package org.pierre.tvmaze.feature.search.domain.usecase.impl

import org.pierre.tvmaze.feature.favorites.domain.usecase.GetFavorites
import org.pierre.tvmaze.feature.search.domain.model.exception.EmptySearchQueryException
import org.pierre.tvmaze.feature.search.domain.repository.SearchRepository
import org.pierre.tvmaze.feature.search.domain.usecase.SaveRecentSearch
import org.pierre.tvmaze.feature.search.domain.usecase.Search
import org.pierre.tvmaze.model.common.MediaItemModel
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.tvmaze.model.data_status.toLoadedData

internal class SearchUseCase(
    private val searchRepository: SearchRepository,
    private val saveRecentSearch: SaveRecentSearch,
    private val getFavorites: GetFavorites
): Search {
    override suspend fun invoke(
        query: String
    ): Result<List<MediaItemModel>> {
        val safeQuery = query.trim()
        return if (safeQuery.isEmpty()) {
            Result.failure(EmptySearchQueryException())
        } else {
            val result = searchRepository.search(safeQuery.replace(" ", "%20"))
            saveRecentSearch(safeQuery)

            result.fold(
                onSuccess = { items ->
                    val favorites = getFavorites()
                    val favoriteIds = favorites.mapNotNull { it.id.toLoadedData() }.toSet()
                    val updated = items.map { item ->
                        val id = item.id.toLoadedData()
                        if (id != null) {
                            item.copy(isFavorite = DataStatus.Loaded(favoriteIds.contains(id)))
                        } else {
                            item
                        }
                    }
                    Result.success(updated)
                },
                onFailure = { throwable ->
                    Result.failure(throwable)
                }
            )
        }
    }
}
