package org.pierre.tvmaze.feature.search.domain.model

data class SearchResultItemModel(
    val name: String,
    val image: String?,
    val year: String,
    val ranting: Float,
    val isFavorite: Boolean,
)
