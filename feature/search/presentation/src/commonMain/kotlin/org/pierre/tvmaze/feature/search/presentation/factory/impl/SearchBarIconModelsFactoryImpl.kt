package org.pierre.tvmaze.feature.search.presentation.factory.impl

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconModelsFactory
import org.pierre.tvmaze.feature.search.presentation.model.SearchBarIconModel
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import tvmaze.feature.search.presentation.generated.resources.Res
import tvmaze.feature.search.presentation.generated.resources.back
import tvmaze.feature.search.presentation.generated.resources.clear
import tvmaze.feature.search.presentation.generated.resources.more_options
import tvmaze.feature.search.presentation.generated.resources.search

class SearchBarIconModelsFactoryImpl : SearchBarIconModelsFactory {

    override fun getExpandedLeadingIconModel(): SearchBarIconModel = SearchBarIconModel(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = Res.string.back,
        uiEvent = SearchUiEvent.OnArrowBackClick
    )

    override fun getNotExpandedLeadingIconModel(): SearchBarIconModel = SearchBarIconModel(
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
