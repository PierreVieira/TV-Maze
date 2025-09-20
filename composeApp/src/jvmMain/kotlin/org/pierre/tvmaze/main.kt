package org.pierre.tvmaze

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.PointerMatcher
import androidx.compose.foundation.onClick
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.dsl.module
import org.pierre.tvmaze.core.room_provider.db.getDatabaseBuilder
import org.pierre.tvmaze.di.initializeKoin

@OptIn(ExperimentalFoundationApi::class)
fun main() {
    initializeKoin(
        platformModules = listOf(
            module {
                single {
                    getDatabaseBuilder()
                }
            }
        )
    )
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "TV Maze",
        ) {
            App(
                getNavigationModifier = { onBack ->
                    Modifier.onClick(
                        matcher = PointerMatcher.mouse(PointerButton.Back),
                        onClick = onBack,
                    )
                }
            )
        }
    }
}
