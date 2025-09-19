import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.pierre.tvmaze.switch_android_color_scheme.presentation"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    buildFeatures {
        compose = true
    }

    // Recommended Java/Kotlin levels for Compose
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
    // Compose Android
    implementation(platform(libs.composeBom))
    implementation(libs.ui)
    implementation(libs.uiGraphics)
    implementation(libs.uiToolingPreview)
    implementation(libs.lifecycleRuntimeCompose)
    implementation(libs.material3)
    implementation(libs.icons)
    implementation(libs.androidx.core.ktx)
    debugImplementation(libs.uiTooling)
    debugImplementation(libs.uiTestManifest)
    // Koin
    implementation(platform(libs.koinBom))
    implementation(libs.koinCore)
    implementation(libs.koinComposeViewModel)

    // Features
    implementation(projects.feature.switchAndroidColorScheme.domain)

    // Ui
    implementation(projects.ui.theme)
    implementation(projects.ui.utils)
    implementation(projects.ui.components.iconButton)
    implementation(projects.ui.components.spacer)
}
