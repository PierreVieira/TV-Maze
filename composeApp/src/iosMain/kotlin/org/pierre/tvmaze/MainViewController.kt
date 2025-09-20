package org.pierre.tvmaze

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.dsl.module
import org.pierre.tvmaze.core.room_provider.db.getDatabaseBuilder
import org.pierre.tvmaze.di.initializeKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController(
    configure = {
        initializeKoin(
            platformModules = listOf(
                module {
                    single {
                        getDatabaseBuilder()
                    }
                }
            )
        )
    },
) {
    App()
}
