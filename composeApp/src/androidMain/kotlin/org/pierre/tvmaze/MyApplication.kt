package org.pierre.tvmaze

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.pierre.tvmaze.core.room_provider.db.getDatabaseBuilder
import org.pierre.tvmaze.di.initializeKoin
import org.pierre.tvmaze.material_you.data.di.materialYouDataModule
import org.pierre.tvmaze.material_you.domain.di.materialYouDomainModule
import org.pierre.tvmaze.material_you.presentation.di.materialYouPresentationModule

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val context = this@MyApplication
        val androidRoomModule = module {
            single { getDatabaseBuilder(context) }
        }
        initializeKoin(
            config = {
                androidContext(context)
            },
            platformModules = listOf(
                androidRoomModule,
                materialYouDataModule,
                materialYouDomainModule,
                materialYouPresentationModule,
            )
        )
    }
}
