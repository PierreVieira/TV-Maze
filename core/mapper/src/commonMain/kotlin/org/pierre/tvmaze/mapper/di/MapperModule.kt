package org.pierre.tvmaze.mapper.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.mapper.ShowItemDatesMapper
import org.pierre.tvmaze.mapper.ShowItemModelMapper
import org.pierre.tvmaze.mapper.StarsMapper
import org.pierre.tvmaze.mapper.impl.ShowItemDatesMapperImpl
import org.pierre.tvmaze.mapper.impl.ShowItemModelMapperImpl
import org.pierre.tvmaze.mapper.impl.StarsMapperImpl

val mapperModule = module {
    factoryOf(::ShowItemModelMapperImpl).bind<ShowItemModelMapper>()
    factoryOf(::StarsMapperImpl).bind<StarsMapper>()
    factoryOf(::ShowItemDatesMapperImpl).bind<ShowItemDatesMapper>()
}
