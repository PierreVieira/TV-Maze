package org.pierre.tvmaze.feature.search.presentation.factory.impl

import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconModelsFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconsFactory
import org.pierre.tvmaze.feature.search.presentation.model.SearchBarIconModel
import org.pierre.tvmaze.feature.search.presentation.model.SearchBarIconsModel

internal class SearchBarIconsFactoryImpl(
    private val searchBarIconModelsFactory: SearchBarIconModelsFactory,
) : SearchBarIconsFactory {

    override fun create(isExpanded: Boolean, query: String): SearchBarIconsModel =
        SearchBarIconsModel(
            leadingIcon = getLeadingIconModel(isExpanded),
            trailingIcon = getTrailingIconModel(isExpanded, query)
        )


    private fun getLeadingIconModel(isExpanded: Boolean): SearchBarIconModel =
        searchBarIconModelsFactory.run {
            if (isExpanded) {
                getExpandedLeadingIconModel()
            } else {
                getNotExpandedLeadingIconModel()
            }
        }

    private fun getTrailingIconModel(isExpanded: Boolean, query: String): SearchBarIconModel? =
        searchBarIconModelsFactory.run {
            if (isExpanded) {
                getCloseModel().takeIf { query.isNotEmpty() }
            } else {
                getMoreOptionsModel()
            }
        }
}
