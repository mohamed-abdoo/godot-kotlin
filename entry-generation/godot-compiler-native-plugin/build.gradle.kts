import com.github.jengelman.gradle.plugins.shadow.ShadowExtension
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("com.github.jengelman.gradle.plugins:shadow:${DependenciesVersions.shadowJarPluginVersion}")
    }
}

plugins {
    kotlin("jvm")
    `maven-publish`
}

apply(plugin = "com.github.johnrengelman.shadow")

val embeddable by configurations.creating {
    extendsFrom(configurations.implementation.get())
}

dependencies {
    godotProjectImplementation(":godot-annotation-processor", project.extra["godotVersion"] as String)
    godotProjectImplementation(":godot-compiler-plugin-common", project.extra["godotVersion"] as String)
    implementation("de.jensklingenberg:mpapt-runtime:${DependenciesVersions.mpaptVersion}")
    compileOnly(kotlin("compiler"))
}

val shadowJar by tasks.getting(ShadowJar::class) {
    configurations = listOf(embeddable)
    @Suppress("UnstableApiUsage")
    manifest {
        attributes["Implementation-Title"] = "Godot Kotlin Native Compiler Plugin"
        attributes["Implementation-Version"] = project.version
        attributes["Main-Class"] = "godot.compilerplugin.NativeComponentRegistrar"
    }
    archiveBaseName.set("godot-compiler-native-plugin")
    archiveVersion.set(project.version.toString())
    val classifier: String? = null //needed as we need to specify the type null represents. otherwise we get ambiguous overload exception during build
    archiveClassifier.set(classifier)
}

tasks {
    build {
        finalizedBy(publishToMavenLocal)
    }
}

publishing {
    publications {
        val shadow by creating(MavenPublication::class) {
            project.extensions.getByType(ShadowExtension::class).component(this)
        }
    }
}
