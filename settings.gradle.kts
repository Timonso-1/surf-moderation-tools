pluginManagement {
    plugins {
        kotlin("jvm") version "2.2.0"
    }
}

rootProject.name = "surf-moderation-tools"

buildscript {
    repositories {
        gradlePluginPortal()
        maven("https://repo.slne.dev/repository/maven-public/") { name = "maven-public" }
    }
    dependencies {
        classpath("dev.slne.surf:surf-api-gradle-plugin:1.21.8+")
    }
}