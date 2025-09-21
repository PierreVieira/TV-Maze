package org.pierre.tvmaze.mapper.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.mapper.MediaItemDatesMapper
import org.pierre.tvmaze.mapper.MediaItemModelMapper
import org.pierre.tvmaze.mapper.MediaResultMapper
import org.pierre.tvmaze.mapper.StarsMapper
import org.pierre.tvmaze.mapper.impl.MediaItemDatesMapperImpl
import org.pierre.tvmaze.mapper.impl.MediaItemModelMapperImpl
import org.pierre.tvmaze.mapper.impl.MediaResultMapperImpl
import org.pierre.tvmaze.mapper.impl.MediaMapperImpl

val mapperModule = module {
    factoryOf(::MediaResultMapperImpl).bind<MediaResultMapper>()
    factoryOf(::MediaMapperImpl).bind<StarsMapper>()
    factoryOf(::MediaItemDatesMapperImpl).bind<MediaItemDatesMapper>()
    factoryOf(::MediaItemModelMapperImpl).bind<MediaItemModelMapper>()
}
