package org.pierre.tvmaze.network.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pierre.tvmaze.network.utils.getHttpClient

val networkModule = module {
    singleOf(::getHttpClient)
}
