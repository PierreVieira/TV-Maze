package org.pierre.tvmaze.feature.search.presentation.factory.impl

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconModelsFactory
import org.pierre.tvmaze.feature.search.presentation.model.bar.SearchBarIconModel
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import tvmaze.feature.search.presentation.generated.resources.Res
import tvmaze.feature.search.presentation.generated.resources.clear
import tvmaze.feature.search.presentation.generated.resources.more_options
import tvmaze.feature.search.presentation.generated.resources.search

class SearchBarIconModelsFactoryImpl : SearchBarIconModelsFactory {

    override fun getLeadingIcon(): SearchBarIconModel = SearchBarIconModel(
        imageVector = Icons.Default.Search,
        contentDescription = Res.string.search,
        uiEvent = SearchUiEvent.OnSearchIconClick
    )

    override fun getCloseModel(): SearchBarIconModel = SearchBarIconModel(
        imageVector = Icons.Default.Close,
        contentDescription = Res.string.clear,
        uiEvent = SearchUiEvent.OnClearClick
    )

    override fun getMoreOptionsModel(): SearchBarIconModel = SearchBarIconModel(
        imageVector = Icons.Default.MoreVert,
        contentDescription = Res.string.more_options,
        uiEvent = SearchUiEvent.OnMoreOptionsClick
    )
}
