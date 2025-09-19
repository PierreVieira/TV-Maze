package org.pierre.tvmaze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.pierre.tvmaze.switch_android_color_scheme.presentation.component.AndroidColorSchemeSwitchComponent
import org.pierre.tvmaze.switch_android_color_scheme.presentation.component.MaterialYouBottomSheet
import org.pierre.tvmaze.switch_android_color_scheme.presentation.model.AndroidColorSchemeUiAction
import org.pierre.tvmaze.switch_android_color_scheme.presentation.viewmodel.AndroidColorSchemeViewModel
import org.pierre.tvmaze.ui.utils.ActionCollector

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private val androidColorSchemeViewModel: AndroidColorSchemeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDynamicColorsOn by androidColorSchemeViewModel.isDynamicColorsOn.collectAsState()
            val onEvent = androidColorSchemeViewModel::onEvent
            val navHostController = rememberNavController()
            val materialYouBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            MaterialYouActionCollector(navHostController, materialYouBottomSheetState)
            App(
                navHostController = navHostController,
                getSpecificColors = { isAppInDarkTheme ->
                    enableEdgeToEdge(statusBarStyle = getStatusBarStyle(isAppInDarkTheme))
                    getAndroidSpecificColorScheme(
                        isDynamicColorsOn = isDynamicColorsOn,
                        isAppInDarkTheme = isAppInDarkTheme
                    )
                },
                switchPlatformColorSchemeComponent = {
                    AndroidColorSchemeSwitchComponent(
                        isChecked = isDynamicColorsOn,
                        onEvent = onEvent
                    )
                },
                extraRoute = { navGraphBuilder ->
                    navGraphBuilder.dialog<AndroidNavRoute.MaterialYouBottomSheet> {
                        MaterialYouBottomSheet(
                            sheetState = materialYouBottomSheetState,
                            isMaterialYouActivated = isDynamicColorsOn,
                            onEvent = onEvent
                        )
                    }
                }
            )
        }
    }

    @Composable
    private fun MaterialYouActionCollector(
        navHostController: NavHostController,
        materialYouBottomSheetState: SheetState
    ) {
        ActionCollector(androidColorSchemeViewModel.uiAction) { uiAction ->
            when (uiAction) {
                AndroidColorSchemeUiAction.CloseBottomSheet -> {
                    if (materialYouBottomSheetState.isVisible) {
                        materialYouBottomSheetState.hide()
                    }
                    navHostController.navigateUp()
                }
                AndroidColorSchemeUiAction.NavigateToMaterialYouBottomSheet ->
                    navHostController.navigate(AndroidNavRoute.MaterialYouBottomSheet)
            }
        }
    }

    @Composable
    private fun getStatusBarStyle(isAppInDarkTheme: Boolean): SystemBarStyle = SystemBarStyle.run {
        val color = Color.Transparent.toArgb()
        if (isAppInDarkTheme) {
            dark(color)
        } else {
            light(color, color)
        }
    }
}
