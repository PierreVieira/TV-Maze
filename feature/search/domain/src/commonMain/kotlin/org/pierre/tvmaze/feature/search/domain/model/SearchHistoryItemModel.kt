package org.pierre.tvmaze.feature.search.domain.model

data class SearchHistoryItemModel(
    val id: Long,
    val query: String,
    val timestamp: Long,
    val searchedRange: IntRange,
)
