package org.pierre.tvmaze.material_you.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.pierre.tvmaze.material_you.domain.model.MaterialYouUseCases
import org.pierre.tvmaze.material_you.domain.usecase.GetIsDynamicColorsEnabledFlow
import org.pierre.tvmaze.material_you.domain.usecase.SetIsDynamicColorsEnabled
import org.pierre.tvmaze.material_you.domain.usecase.impl.GetIsDynamicColorsEnabledFlowUseCase
import org.pierre.tvmaze.material_you.domain.usecase.impl.SetIsDynamicColorsEnabledUseCase

val materialYouDomainModule = module {
    factoryOf(::MaterialYouUseCases)
    factoryOf(::GetIsDynamicColorsEnabledFlowUseCase).bind<GetIsDynamicColorsEnabledFlow>()
    factoryOf(::SetIsDynamicColorsEnabledUseCase).bind<SetIsDynamicColorsEnabled>()
}
