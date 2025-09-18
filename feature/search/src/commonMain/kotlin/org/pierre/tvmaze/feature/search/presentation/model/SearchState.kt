package org.pierre.tvmaze.feature.search.presentation.model

data class SearchState(
    val query: String,
    val isExpanded: Boolean,
    val searchResults: List<String>
)
