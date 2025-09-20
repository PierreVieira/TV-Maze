package org.pierre.tvmaze.feature.search.presentation.model

import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition

data class SearchState(
    val query: String,
    val isShowingMenu: Boolean,
    val content: SearchContent,
    val iconsModel: SearchBarIconsModel,
    val searchBarPosition: SearchBarPosition,
)
