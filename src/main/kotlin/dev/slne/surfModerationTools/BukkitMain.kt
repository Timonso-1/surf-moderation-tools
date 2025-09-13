package dev.slne.surfModerationTools

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surfModerationTools.commands.rotateCommand
import org.bukkit.plugin.java.JavaPlugin
import kotlin.jvm.java

val plugin get() = JavaPlugin.getPlugin(BukkitMain::class.java)

class BukkitMain : SuspendingJavaPlugin() {

    override suspend fun onEnableAsync() {
        rotateCommand()
    }

    override suspend fun onDisableAsync() {
    }
}
