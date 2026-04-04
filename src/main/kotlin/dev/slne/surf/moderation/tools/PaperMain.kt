package dev.slne.surf.moderation.tools

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.api.paper.event.register
import dev.slne.surf.moderation.tools.commands.faqCommand
import dev.slne.surf.moderation.tools.commands.freezeCommand
import dev.slne.surf.moderation.tools.commands.pingPlayerCommand
import dev.slne.surf.moderation.tools.commands.rotateCommand
import dev.slne.surf.moderation.tools.commands.stopInteraction
import dev.slne.surf.moderation.tools.commands.surfModerationToolsCommand
import dev.slne.surf.moderation.tools.commands.unfreezeCommand
import dev.slne.surf.moderation.tools.config.SurfModerationToolConfig
import dev.slne.surf.moderation.tools.listener.PlayerActionListener
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
        stopInteraction()
        pingPlayerCommand()

        PlayerActionListener.register()
    }
}
