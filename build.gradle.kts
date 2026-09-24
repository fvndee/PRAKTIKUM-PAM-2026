import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    kotlin("jvm") version "2.4.20" apply false
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }

    dependencies {
        "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    }

    configure<KotlinJvmProjectExtension> {
        jvmToolchain(21)
    }
}
