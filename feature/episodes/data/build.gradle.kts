plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

kotlin {
    androidTarget()

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "EpisodesData"
            isStatic = true
        }
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.core)

            // Koin (feature DI)
            implementation(project.dependencies.platform(libs.koinBom))
            implementation(libs.koinCore)

            // Core
            implementation(libs.ktor.client.core)
            implementation(projects.core.network)
            implementation(projects.core.dto)
            implementation(projects.core.model.common)
            implementation(projects.core.model.dataStatus)
            implementation(projects.core.mapper)
            implementation(projects.core.provider.room)

            // Features
            implementation(projects.feature.episodes.domain)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.ktor.client.mock)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.content.serialization.json)
        }
    }
}

android {
    namespace = "org.pierre.tvmaze.feature.episodes.data"
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
