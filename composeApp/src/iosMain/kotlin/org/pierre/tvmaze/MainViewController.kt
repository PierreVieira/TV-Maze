package org.pierre.tvmaze

import androidx.compose.ui.window.ComposeUIViewController
import org.pierre.tvmaze.di.initializeKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController(
    configure = { initializeKoin() }
) {
    App()
}
