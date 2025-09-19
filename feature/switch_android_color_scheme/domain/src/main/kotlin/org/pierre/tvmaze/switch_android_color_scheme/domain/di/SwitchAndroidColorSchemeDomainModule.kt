package org.pierre.tvmaze.switch_android_color_scheme.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.switch_android_color_scheme.domain.model.AndroidColorSchemeUseCases
import org.pierre.tvmaze.switch_android_color_scheme.domain.usecase.GetIsDynamicColorsEnabledFlow
import org.pierre.tvmaze.switch_android_color_scheme.domain.usecase.SetIsDynamicColorsEnabled
import org.pierre.tvmaze.switch_android_color_scheme.domain.usecase.impl.GetIsDynamicColorsEnabledFlowUseCase
import org.pierre.tvmaze.switch_android_color_scheme.domain.usecase.impl.SetIsDynamicColorsEnabledUseCase

val switchAndroidColorSchemeDomainModule = module {
    factoryOf(::AndroidColorSchemeUseCases)
    factoryOf(::GetIsDynamicColorsEnabledFlowUseCase).bind<GetIsDynamicColorsEnabledFlow>()
    factoryOf(::SetIsDynamicColorsEnabledUseCase).bind<SetIsDynamicColorsEnabled>()
}
