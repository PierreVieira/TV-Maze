package org.pierre.tvmaze.feature.search.presentation.factory.impl

import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel
import org.pierre.tvmaze.feature.search.presentation.factory.NewSearchStateFromHistoryFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchHistoryContentFactory
import org.pierre.tvmaze.feature.search.presentation.model.SearchContent
import org.pierre.tvmaze.feature.search.presentation.model.SearchState

class NewSearchStateFromHistoryFactoryImpl(
    private val searchHistoryContentFactory: SearchHistoryContentFactory,
) : NewSearchStateFromHistoryFactory {

    override fun create(
        searchHistory: List<SearchHistoryItemModel>,
        currentState: SearchState,
    ): SearchState =
        when (currentState.content) {
            SearchContent.Loading,
            is SearchContent.History,
            is SearchContent.Error.NoHistory,
                -> {
                val query = currentState.searchBar.query
                currentState.copy(
                    content = searchHistoryContentFactory.create(searchHistory, query)
                )
            }

            is SearchContent.Error.NoHistoryForSpecificQuery,
            is SearchContent.SearchResults,
                -> currentState
        }
}
