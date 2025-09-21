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
            baseName = "Navigation"
            isStatic = true
        }
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            // Compose
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.foundation)

            // Navigation
            implementation(libs.navigation.compose)

            // Features
            implementation(projects.feature.main)
            implementation(projects.feature.episodes.domain)
            implementation(projects.feature.search.warning.deleteItem.presentation)
            implementation(projects.feature.search.warning.deleteAll.presentation)
            implementation(projects.feature.mediaDetails.presentation)

            // Core
            implementation(projects.core.utils)
            implementation(projects.core.model.common)

            // UI
            implementation(projects.ui.utils)

            // Koin
            implementation(project.dependencies.platform(libs.koinBom))
            implementation(libs.koinCore)
            implementation(libs.koinComposeViewModel)

        }
    }
}

android {
    namespace = "org.pierre.tvmaze.ui.navigation"
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
