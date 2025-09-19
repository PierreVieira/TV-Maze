import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.pierre.tvmaze.switch_android_color_scheme.data"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }


    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_21
        }
    }
}

dependencies {
    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // Koin
    implementation(platform(libs.koinBom))
    implementation(libs.koinCore)

    // Data Store
    implementation(libs.dataStore)
    implementation(libs.dataStore.preferences)
    implementation(libs.androidx.core.ktx)

    // Features
    implementation(projects.feature.switchAndroidColorScheme.domain)

    // Core
    implementation(projects.core.utils)
}
