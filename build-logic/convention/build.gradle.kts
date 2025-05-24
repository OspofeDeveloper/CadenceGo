import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("koin") {
            id = libs.plugins.cadencego.koin.get().pluginId
            implementationClass = "KoinConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.cadencego.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}

