rootProject.name = "TVMaze"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":composeApp")

// Core
include(":core:data_store_provider")
include(":core:network")
include(":core:utils")
include(":core:koin_initializer")
include(":core:model:theme")

// UI
include(":ui:theme")
include(":ui:navigation")
include(":ui:components:icon_button")

// Features
include(":feature:main")
include(":feature:favorites:presentation")
include(":feature:search:presentation")
include(":feature:theme_selection:presentation")
include(":feature:theme_selection:domain")
include(":feature:theme_selection:data")
include(":feature:search:domain")
include(":feature:search:data")
include(":feature:switch_android_color_scheme:data")
include(":feature:switch_android_color_scheme:domain")
include(":feature:switch_android_color_scheme:presentation")
include(":ui:utils")
include(":ui:components:spacer")
