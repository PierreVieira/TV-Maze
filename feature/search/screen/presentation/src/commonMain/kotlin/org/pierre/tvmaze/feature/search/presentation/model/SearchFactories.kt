package org.pierre.tvmaze.feature.search.presentation.model

import org.pierre.tvmaze.feature.search.presentation.factory.InitialSearchStateFactory
import org.pierre.tvmaze.feature.search.presentation.factory.NewSearchStateFromHistoryFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconsFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchHistoryContentFactory

data class SearchFactories(
    val initialState: InitialSearchStateFactory,
    val searchBarIcons: SearchBarIconsFactory,
    val searchHistoryContent: SearchHistoryContentFactory,
    val newSearchStateFromHistory: NewSearchStateFromHistoryFactory,
)
