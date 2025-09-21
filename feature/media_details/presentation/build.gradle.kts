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
            baseName = "MediaDetailsPresentation"
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
            implementation(compose.components.uiToolingPreview)

            // Navigation
            implementation(libs.navigation.compose)

            // Koin (feature DI)
            implementation(project.dependencies.platform(libs.koinBom))
            implementation(libs.koinCore)
            implementation(libs.koinComposeViewModel)

            // Coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network)

            // Core models
            implementation(projects.core.model.common)
            implementation(projects.core.model.dataStatus)

            // Features
            implementation(projects.feature.mediaDetails.domain)
            implementation(projects.feature.favorites.domain)

            // Components
            implementation(projects.ui.utils)
            implementation(projects.ui.components.spacer)
            implementation(projects.ui.components.stars)
            implementation(projects.ui.components.shimmer)
            implementation(projects.ui.components.iconButton)
            implementation(projects.ui.components.mediaItemCard)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.pierre.tvmaze.feature.media_details.presentation"
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
