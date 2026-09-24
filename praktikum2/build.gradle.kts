plugins {
    kotlin("jvm")
    id("application")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
}

application {
    mainClass.set("PraktikumKt")
}
