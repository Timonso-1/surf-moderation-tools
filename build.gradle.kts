import dev.slne.surf.surfapi.gradle.util.registerRequired
import dev.slne.surf.surfapi.gradle.util.withSurfApiBukkit

plugins {
    id("dev.slne.surf.surfapi.gradle.paper-plugin")
}

group = "dev.slne.surf.moderation.tools"
version = findProperty("version") as String


dependencies {
    compileOnly(libs.surf.bitmap)
}

surfPaperPluginApi {
    mainClass("dev.slne.surf.moderation.tools.BukkitMain")
    foliaSupported(true)
    generateLibraryLoader(false)
    authors.add(listOf("MikeyLLP", "Red", "Timonso").toString())

    serverDependencies {
        registerRequired("surf-bitmap-provider-paper")
    }

    runServer {
        withSurfApiBukkit()
    }
}