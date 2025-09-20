package org.pierre.tvmaze.feature.search.presentation.model

import org.pierre.tvmaze.model.common.ShowItemModel

sealed interface SearchContent {
    data class SearchResult(val searchItems: List<ShowItemModel>) : SearchContent
    data object NoResults : SearchContent
    data object NoHistory : SearchContent
}
