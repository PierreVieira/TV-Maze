plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget()

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "MainFeature"
            isStatic = true
        }
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            // Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.materialIconsExtended)

            // Navigation
            implementation(libs.navigation.compose)
            implementation(compose.material3AdaptiveNavigationSuite)

            // Koin
            implementation(project.dependencies.platform(libs.koinBom))
            implementation(libs.koinCore)
            implementation(libs.koinComposeViewModel)

            // Core dependencies
            implementation(projects.core.utils)

            // Feature dependencies
            implementation(projects.feature.search.warning.deleteItem.presentation)
            implementation(projects.feature.themeSelection.presentation)
            implementation(projects.feature.favorites.presentation)
            implementation(projects.feature.search.screen.domain)
            implementation(projects.feature.search.screen.presentation)

            // UI
            implementation(projects.ui.theme)
        }
    }
}

android {
    namespace = "org.pierre.tvmaze.feature.main"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}
