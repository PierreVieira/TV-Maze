plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.androidGradlePlugin)
    implementation(libs.kotlinGradlePlugin)
}

gradlePlugin {
    plugins {
        create("androidCommonConfig") {
            id = "com.quare.android.common"
            implementationClass = "com.quare.blitzsplit.plugins.AndroidCommonConfigPlugin"
        }
    }
}
