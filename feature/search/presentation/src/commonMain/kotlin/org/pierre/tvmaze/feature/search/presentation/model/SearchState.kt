package org.pierre.tvmaze.feature.search.presentation.model

import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition

data class SearchState(
    val query: String,
    val isExpanded: Boolean,
    val isShowingMenu: Boolean,
    val searchResults: List<String>,
    val iconsModel: SearchBarIconsModel,
    val searchBarPosition: SearchBarPosition,
)
