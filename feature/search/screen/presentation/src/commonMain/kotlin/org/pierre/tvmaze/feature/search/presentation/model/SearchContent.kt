package org.pierre.tvmaze.feature.search.presentation.model

import org.jetbrains.compose.resources.StringResource
import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel
import org.pierre.tvmaze.model.common.ShowItemModel
import tvmaze.feature.search.screen.presentation.generated.resources.Res
import tvmaze.feature.search.screen.presentation.generated.resources.no_history_for_specific_query
import tvmaze.feature.search.screen.presentation.generated.resources.no_history_message

sealed interface SearchContent {
    data class SearchResults(val data: List<ShowItemModel>) : SearchContent
    data class History(val data: List<SearchHistoryItemModel>) : SearchContent
    sealed class Error(val errorResourceId: StringResource) : SearchContent {
        data class NoHistoryForSpecificQuery(val query: String) :
            Error(Res.string.no_history_for_specific_query)

        data object NoHistory : Error(Res.string.no_history_message)
    }

    data object Loading : SearchContent
}
