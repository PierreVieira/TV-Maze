package org.pierre.feature.search.warning.delete_item.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.pierre.feature.search.warning.delete_item.presentation.viewmodel.DeleteSearchItemViewModel

val deleteItemSearchPresentationModule = module {
    viewModelOf(::DeleteSearchItemViewModel)
}
