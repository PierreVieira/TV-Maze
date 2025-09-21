package org.pierre.tvmaze.feature.search.domain.model

import org.pierre.tvmaze.feature.favorites.domain.usecase.GetFavoritesFlow
import org.pierre.tvmaze.feature.favorites.domain.usecase.ToggleFavorite
import org.pierre.tvmaze.feature.search.domain.usecase.GetDurationDisappearMenuDuration
import org.pierre.tvmaze.feature.search.domain.usecase.GetErrorTypeFromThrowable
import org.pierre.tvmaze.feature.search.domain.usecase.GetNewSearchBarPositionDueToToggle
import org.pierre.tvmaze.feature.search.domain.usecase.GetSearchBarPositionFlow
import org.pierre.tvmaze.feature.search.domain.usecase.GetSearchHistoryFlow
import org.pierre.tvmaze.feature.search.domain.usecase.GetSearchItemsLoading
import org.pierre.tvmaze.feature.search.domain.usecase.SaveNewSearchBarPosition
import org.pierre.tvmaze.feature.search.domain.usecase.Search

data class SearchUseCases(
    val search: Search,
    val getNewSearchBarPositionDueToToggle: GetNewSearchBarPositionDueToToggle,
    val saveNewSearchBarPosition: SaveNewSearchBarPosition,
    val getSearchBarPositionFlow: GetSearchBarPositionFlow,
    val getDurationDisappearMenuDuration: GetDurationDisappearMenuDuration,
    val getErrorTypeFromThrowable: GetErrorTypeFromThrowable,
    val getSearchItemsLoading: GetSearchItemsLoading,
    val getSearchHistoryFlow: GetSearchHistoryFlow,
    val toggleFavorite: ToggleFavorite,
    val getFavoritesFlow: GetFavoritesFlow
)
