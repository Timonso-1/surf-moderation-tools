package dev.slne.surf.moderation.tools

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.moderation.tools.commands.*
import dev.slne.surf.moderation.tools.config.SurfModerationToolConfig
import dev.slne.surf.moderation.tools.listener.PlayerActionListener
import dev.slne.surf.surfapi.bukkit.api.event.register
import org.bukkit.plugin.java.JavaPlugin

val plugin get() = JavaPlugin.getPlugin(PaperMain::class.java)

class PaperMain : SuspendingJavaPlugin() {
    override suspend fun onLoadAsync() {
        SurfModerationToolConfig.init()
    }

    override suspend fun onEnableAsync() {
        surfModerationToolsCommand()
        faqCommand()
        rotateCommand()
        freezeCommand()
        unfreezeCommand()

        PlayerActionListener.register()
    }
}
