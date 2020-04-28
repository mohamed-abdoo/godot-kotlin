plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("gradle-plugin", version = "1.3.71"))
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.0")

    //Remember to change version according to DependenciesVersion
    implementation("com.squareup:kotlinpoet:1.5.0")
}
