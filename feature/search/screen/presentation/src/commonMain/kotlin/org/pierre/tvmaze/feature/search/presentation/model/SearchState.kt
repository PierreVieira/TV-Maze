package org.pierre.tvmaze.feature.search.presentation.model

import org.pierre.tvmaze.feature.search.presentation.model.bar.SearchBarState

data class SearchState(
    val content: SearchContent,
    val searchBar: SearchBarState,
)
