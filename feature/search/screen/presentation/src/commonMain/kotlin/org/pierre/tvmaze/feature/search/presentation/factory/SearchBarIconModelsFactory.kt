package org.pierre.tvmaze.feature.search.presentation.factory

import org.pierre.tvmaze.feature.search.presentation.model.bar.SearchBarIconModel

interface SearchBarIconModelsFactory {
    fun getLeadingIcon(): SearchBarIconModel
    fun getCloseModel(): SearchBarIconModel
    fun getMoreOptionsModel(): SearchBarIconModel
}
