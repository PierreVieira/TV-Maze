package org.pierre.tvmaze.feature.search.presentation.factory.impl

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconsFactory
import org.pierre.tvmaze.feature.search.presentation.model.SearchBarIconModel
import org.pierre.tvmaze.feature.search.presentation.model.SearchBarIconsModel
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import tvmaze.feature.search.presentation.generated.resources.Res
import tvmaze.feature.search.presentation.generated.resources.back
import tvmaze.feature.search.presentation.generated.resources.search
import tvmaze.feature.search.presentation.generated.resources.clear

internal class SearchBarIconsFactoryImpl : SearchBarIconsFactory {

    override fun create(isExpanded: Boolean, query: String): SearchBarIconsModel =
        SearchBarIconsModel(
            leadingIcon = getLeadingIconModel(isExpanded),
            trailingIcon = getTrailingIconModel(isExpanded, query)
        )


    private fun getLeadingIconModel(isExpanded: Boolean): SearchBarIconModel = if (isExpanded) {
        getExpandedLeadingIconModel()
    } else {
        getNotExpandedLeadingIconModel()
    }

    private fun getNotExpandedLeadingIconModel(): SearchBarIconModel = SearchBarIconModel(
        imageVector = Icons.Default.Search,
        contentDescription = Res.string.search,
        uiEvent = SearchUiEvent.OnSearchIconClick
    )

    private fun getExpandedLeadingIconModel(): SearchBarIconModel = SearchBarIconModel(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = Res.string.back,
        uiEvent = SearchUiEvent.OnArrowBackClick
    )

    private fun getTrailingIconModel(isExpanded: Boolean, query: String): SearchBarIconModel? =
        SearchBarIconModel(
            imageVector = Icons.Default.Close,
            contentDescription = Res.string.clear,
            uiEvent = SearchUiEvent.OnClearClick
        ).takeIf { isExpanded && query.isNotEmpty() }
}
