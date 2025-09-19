package org.pierre.tvmaze

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.pierre.tvmaze.di.initializeKoin
import org.pierre.tvmaze.material_you.data.di.materialYouDataModule
import org.pierre.tvmaze.material_you.domain.di.materialYouDomainModule
import org.pierre.tvmaze.material_you.presentation.di.switchAndroidColorSchemePresentationModule

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin(
            config = {
                androidContext(this@MyApplication)
            },
            platformModules = listOf(
                materialYouDataModule,
                materialYouDomainModule,
                switchAndroidColorSchemePresentationModule,
            )
        )
    }
}
