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
include(":core:dto")
include(":core:mapper")
include(":core:model:common")
include(":core:model:data_status")
include(":core:model:theme")
include(":core:network")
include(":core:provider:data_store")
include(":core:provider:koin")
include(":core:provider:room")
include(":core:utils")

// UI
include(":ui:components:icon_button")
include(":ui:components:media_item_card")
include(":ui:components:picture")
include(":ui:components:shimmer")
include(":ui:components:spacer")
include(":ui:components:stars")
include(":ui:navigation")
include(":ui:theme")
include(":ui:utils")

// Features
include(":feature:favorites:data")
include(":feature:favorites:domain")
include(":feature:favorites:presentation")
include(":feature:main")
include(":feature:material_you:data")
include(":feature:material_you:domain")
include(":feature:material_you:presentation")
include(":feature:media_details:data")
include(":feature:media_details:domain")
include(":feature:media_details:presentation")
include(":feature:search:screen:data")
include(":feature:search:screen:domain")
include(":feature:search:screen:presentation")
include(":feature:search:warning:delete_all:data")
include(":feature:search:warning:delete_all:domain")
include(":feature:search:warning:delete_all:presentation")
include(":feature:search:warning:delete_item:data")
include(":feature:search:warning:delete_item:domain")
include(":feature:search:warning:delete_item:presentation")
include(":feature:theme_selection:data")
include(":feature:theme_selection:domain")
include(":feature:theme_selection:presentation")
