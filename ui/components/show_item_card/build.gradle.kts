plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget()

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ShowItemCardComponent"
            isStatic = true
        }
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)

            // Coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network)

            // Core
            implementation(projects.core.model.dataStatus)
            implementation(projects.core.model.common)

            // Ui
            implementation(projects.ui.components.shimmer)
            implementation(projects.ui.components.stars)
            implementation(projects.ui.components.picture)
            implementation(projects.ui.components.iconButton)
            implementation(projects.ui.components.spacer)
        }
    }
}

android {
    namespace = "org.pierre.tvmaze.ui.components.show_item_card"
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
