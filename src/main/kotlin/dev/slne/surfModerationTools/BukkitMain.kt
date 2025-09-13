package dev.slne.surfModerationTools

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surfModerationTools.commands.freezeCommand
import dev.slne.surfModerationTools.commands.rotateCommand
import dev.slne.surfModerationTools.commands.unfreezeCommand
import dev.slne.surfModerationTools.listener.PlayerActionListener
import org.bukkit.plugin.java.JavaPlugin
import kotlin.jvm.java

val plugin get() = JavaPlugin.getPlugin(BukkitMain::class.java)

class BukkitMain : SuspendingJavaPlugin() {

    override suspend fun onEnableAsync() {

        rotateCommand()
        freezeCommand()
        unfreezeCommand()

        val manager = server.pluginManager

        manager.registerEvents(PlayerActionListener(), this)

        logger.info("${this.name} Successfully enabled.")
    }

    override suspend fun onDisableAsync() {
        logger.info("Bye <3")
    }
}
