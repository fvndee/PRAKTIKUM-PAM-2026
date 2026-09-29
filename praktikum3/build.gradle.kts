import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(project(":shared"))

    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutinesSwing)

    implementation(libs.compose.material3)
    implementation(libs.compose.uiToolingPreview)
    implementation("org.jetbrains.compose.material:material-icons-extended-desktop:1.7.1")
}

compose.desktop {
    application {
        mainClass = "com.example.p3.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.example.p3"
            packageVersion = "1.0.0"
        }
    }
}