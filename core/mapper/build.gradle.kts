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
            baseName = "CoreMapper"
            isStatic = true
        }
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            // Koin
            implementation(project.dependencies.platform(libs.koinBom))
            implementation(libs.koinCore)

            // Core
            implementation(projects.core.dto)
            implementation(projects.core.utils)
            implementation(projects.core.model.common)
            implementation(projects.core.model.dataStatus)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.pierre.tvmaze.core.mapper"
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
