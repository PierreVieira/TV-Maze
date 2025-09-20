package org.pierre.tvmaze.feature.search.presentation.factory.impl

import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconModelsFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconsFactory
import org.pierre.tvmaze.feature.search.presentation.model.SearchBarIconModel
import org.pierre.tvmaze.feature.search.presentation.model.SearchBarIconsModel

internal class SearchBarIconsFactoryImpl(
    private val searchBarIconModelsFactory: SearchBarIconModelsFactory,
) : SearchBarIconsFactory {

    override fun create(query: String): SearchBarIconsModel =
        SearchBarIconsModel(
            leadingIcon = searchBarIconModelsFactory.getLeadingIcon(),
            trailingIcon = getTrailingIconModel(query)
        )


    private fun getTrailingIconModel(query: String): SearchBarIconModel =
        searchBarIconModelsFactory.run {
            if (query.isNotEmpty()) {
                getCloseModel()
            } else {
                getMoreOptionsModel()
            }
        }
}
