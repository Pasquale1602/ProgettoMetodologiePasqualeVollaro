

plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit)
    implementation(libs.guava)
    implementation("com.google.code.gson:gson:2.11.0")
}

javafx {
    version = "23"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.media")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

application {
    mainClass = "it.unicam.cs.mpgc.rpg129696.App"
}