package dev.slne.surfModerationTools

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surfModerationTools.commands.surfModerationToolCommand
import org.bukkit.plugin.java.JavaPlugin
import kotlin.jvm.java

val plugin get() = JavaPlugin.getPlugin(SurfModerationTools::class.java)

class SurfModerationTools : SuspendingJavaPlugin() {

    override suspend fun onEnableAsync() {
        surfModerationToolCommand()
    }

    override suspend fun onDisableAsync() {

    }
}
