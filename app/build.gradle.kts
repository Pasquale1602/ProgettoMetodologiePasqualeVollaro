plugins {
    application
}

repositories {
    mavenCentral()
}

val javafxVersion = "23"
val os = System.getProperty("os.name").lowercase()
val platform = when {
    os.contains("win") -> "win"
    os.contains("mac") -> "mac"
    else -> "linux"
}

dependencies {
    testImplementation(libs.junit)
    implementation(libs.guava)

    implementation("org.openjfx:javafx-controls:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-fxml:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-media:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-graphics:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-base:$javafxVersion:$platform")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

application {
    mainClass = "org.example.App"
    applicationDefaultJvmArgs = listOf(
        "--module-path", configurations.runtimeClasspath.get().asPath,
        "--add-modules", "javafx.controls,javafx.fxml,javafx.media"
    )
}