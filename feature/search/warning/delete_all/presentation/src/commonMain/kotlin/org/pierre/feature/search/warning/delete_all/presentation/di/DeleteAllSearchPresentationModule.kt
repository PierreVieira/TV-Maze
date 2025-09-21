package org.pierre.feature.search.warning.delete_all.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.pierre.feature.search.warning.delete_all.presentation.viewmodel.DeleteAllSearchViewModel

val deleteAllSearchPresentationModule = module {
    viewModelOf(::DeleteAllSearchViewModel)
}
