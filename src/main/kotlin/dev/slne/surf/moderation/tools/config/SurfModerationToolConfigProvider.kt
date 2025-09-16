package dev.slne.surf.moderation.tools.config

import dev.slne.surf.moderation.tools.config.configs.SurfModerationToolConfig
import dev.slne.surf.moderation.tools.plugin
import dev.slne.surf.surfapi.core.api.config.manager.SpongeConfigManager
import dev.slne.surf.surfapi.core.api.config.surfConfigApi

class SurfModerationToolConfigProvider {
    private val configManager: SpongeConfigManager<SurfModerationToolConfig>

    init {
        surfConfigApi.createSpongeYmlConfig(
            SurfModerationToolConfig::class.java,
            plugin.dataPath,
            "config.yml"
        )
        configManager = surfConfigApi.getSpongeConfigManagerForConfig(
            SurfModerationToolConfig::class.java
        )
        reload()
    }

    fun edit(actions: SurfModerationToolConfig.() -> Unit) {
        configManager.config = configManager.config.apply { actions() }
        configManager.save()
    }

    fun reload() {
        configManager.reloadFromFile()
    }

    val config get() = configManager.config
}