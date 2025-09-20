package org.pierre.tvmaze.feature.search.presentation.model.bar

import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition

data class SearchBarState(
    val query: String,
    val isShowingMenu: Boolean,
    val iconsModel: SearchBarIconsModel,
    val position: SearchBarPosition,
)
