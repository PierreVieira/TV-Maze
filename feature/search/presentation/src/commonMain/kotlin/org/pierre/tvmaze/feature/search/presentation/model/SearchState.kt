package org.pierre.tvmaze.feature.search.presentation.model

import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.model.common.ShowItemModel

data class SearchState(
    val query: String,
    val isShowingMenu: Boolean,
    val searchItems: List<ShowItemModel>,
    val iconsModel: SearchBarIconsModel,
    val searchBarPosition: SearchBarPosition,
)
