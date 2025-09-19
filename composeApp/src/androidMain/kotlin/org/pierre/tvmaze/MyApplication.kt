package org.pierre.tvmaze

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.pierre.tvmaze.di.initializeKoin
import org.pierre.tvmaze.switch_android_color_scheme.data.di.switchAndroidColorSchemeDataModule
import org.pierre.tvmaze.switch_android_color_scheme.domain.di.switchAndroidColorSchemeDomainModule
import org.pierre.tvmaze.switch_android_color_scheme.presentation.di.switchAndroidColorSchemePresentationModule

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin(
            config = {
                androidContext(this@MyApplication)
            },
            platformModules = listOf(
                switchAndroidColorSchemeDataModule,
                switchAndroidColorSchemeDomainModule,
                switchAndroidColorSchemePresentationModule,
            )
        )
    }
}
