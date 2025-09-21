package org.pierre.tvmaze.mapper.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.mapper.EpisodeMapper
import org.pierre.tvmaze.mapper.MediaItemDatesMapper
import org.pierre.tvmaze.mapper.MediaItemModelMapper
import org.pierre.tvmaze.mapper.MediaResultMapper
import org.pierre.tvmaze.mapper.StarsMapper
import org.pierre.tvmaze.mapper.impl.MediaItemDatesMapperImpl
import org.pierre.tvmaze.mapper.impl.MediaItemModelMapperImpl
import org.pierre.tvmaze.mapper.impl.MediaResultMapperImpl
import org.pierre.tvmaze.mapper.impl.StarsMapperImpl
import org.pierre.tvmaze.mapper.HtmlTextCleaner
import org.pierre.tvmaze.mapper.MediaModelMapper
import org.pierre.tvmaze.mapper.impl.EpisodeMapperImpl
import org.pierre.tvmaze.mapper.impl.HtmlTextCleanerImpl
import org.pierre.tvmaze.mapper.impl.MediaModelMapperImpl

val mapperModule = module {
    factoryOf(::MediaResultMapperImpl).bind<MediaResultMapper>()
    factoryOf(::StarsMapperImpl).bind<StarsMapper>()
    factoryOf(::MediaItemDatesMapperImpl).bind<MediaItemDatesMapper>()
    factoryOf(::HtmlTextCleanerImpl).bind<HtmlTextCleaner>()
    factoryOf(::MediaItemModelMapperImpl).bind<MediaItemModelMapper>()
    factoryOf(::MediaModelMapperImpl).bind<MediaModelMapper>()
    factoryOf(::EpisodeMapperImpl).bind<EpisodeMapper>()
}
