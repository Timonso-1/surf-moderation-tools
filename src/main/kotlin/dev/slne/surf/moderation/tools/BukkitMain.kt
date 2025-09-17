package dev.slne.surf.moderation.tools

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.moderation.tools.commands.*
import dev.slne.surf.moderation.tools.config.SurfModerationToolConfigProvider
import dev.slne.surf.moderation.tools.listener.PlayerActionListener
import dev.slne.surf.surfapi.bukkit.api.event.register
import org.bukkit.plugin.java.JavaPlugin

val plugin get() = JavaPlugin.getPlugin(BukkitMain::class.java)

class BukkitMain : SuspendingJavaPlugin() {
    override suspend fun onEnableAsync() {

        configCommand()
        faqCommand()
        rotateCommand()
        freezeCommand()
        unfreezeCommand()


        PlayerActionListener().register()

    }

    override suspend fun onDisableAsync() {
    }

    val moderationToolConfig = SurfModerationToolConfigProvider()
}
