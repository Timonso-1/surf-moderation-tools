import dev.slne.surf.surfapi.gradle.util.registerRequired
import dev.slne.surf.surfapi.gradle.util.withSurfApiBukkit

plugins {
    id("dev.slne.surf.surfapi.gradle.paper-plugin")
}

group = "dev.slne.surf"
version = findProperty("version") as String

dependencies {
    compileOnly(libs.surf.bitmap)
}

surfPaperPluginApi {
    mainClass("dev.slne.surf.moderation.tools.BukkitMain")
    generateLibraryLoader(false)
    authors.add("MikeyLLP")

    serverDependencies {
        registerRequired("surf-bitmap-provider-paper")
    }

    runServer {
        withSurfApiBukkit()
    }
}