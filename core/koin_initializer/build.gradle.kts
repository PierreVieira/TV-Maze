plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget()

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "KoinInitializer"
            isStatic = true
        }
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            // Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Koin
            implementation(project.dependencies.platform(libs.koinBom))
            implementation(libs.koinCore)

            // Feature dependencies
            implementation(projects.feature.main)
            implementation(projects.feature.search.data)
            implementation(projects.feature.search.domain)
            implementation(projects.feature.search.presentation)
            implementation(projects.feature.themeSelection.data)
            implementation(projects.feature.themeSelection.domain)
            implementation(projects.feature.themeSelection.presentation)

            // Core dependencies
            implementation(projects.core.dataStoreProvider)
            implementation(projects.core.mapper)
            implementation(projects.core.network)
        }
    }
}

android {
    namespace = "org.pierre.tvmaze.core.koin_initializer"
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
