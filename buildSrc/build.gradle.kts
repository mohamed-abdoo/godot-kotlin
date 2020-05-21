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
    implementation("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.5")
}
