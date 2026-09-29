<<<<<<< HEAD
rootProject.name = "pertemuan-2-coroutines-flow"

include(
    "handson1-latihan",
    "handson2-latihan",
    "handson3-latihan",
    "praktikum2"
)
=======
rootProject.name = "P3"

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

include(":androidApp")
include(":praktikum3")
include(":shared")
>>>>>>> 599120b (Praktikum ke 3)
