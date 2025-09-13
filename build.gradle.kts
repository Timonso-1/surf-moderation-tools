import dev.slne.surf.surfapi.gradle.util.withSurfApiBukkit

plugins {
    id("dev.slne.surf.surfapi.gradle.paper-plugin")
}

group = "dev.slne"

surfPaperPluginApi {
    mainClass("dev.slne.surfModerationTools.SurfModerationTools")
    generateLibraryLoader(false)
    authors.add("MikeyLLP")

    runServer {
        withSurfApiBukkit()
    }
}