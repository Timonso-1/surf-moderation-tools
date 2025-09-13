package dev.slne.surfModerationTools.commands

import com.github.shynixn.mccoroutine.folia.launch
import com.github.shynixn.mccoroutine.folia.regionDispatcher
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.getValue
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import dev.slne.surfModerationTools.permissions.Permissions
import dev.slne.surfModerationTools.plugin
import kotlinx.coroutines.withContext
import org.bukkit.OfflinePlayer


fun rotateCommand() = commandAPICommand("playerRotate") {
    withArguments(PlayerArgument("player"))
    withPermission(Permissions.COMMAND_ROTATE)

    anyExecutor { sender, args ->
        val targetPlayer: OfflinePlayer by args

        val targetLocation = targetPlayer.player?.location
        val randomYaw = (-180..180).random().toFloat()
        val randomPitch = (-90..90).random().toFloat()

        plugin.launch {
            if (targetLocation != null) {
                withContext(plugin.regionDispatcher(targetLocation)) {
                    targetLocation.setRotation(randomYaw, randomPitch)
                    targetPlayer.player?.teleport(targetLocation)
                }

                sender.sendText {
                    appendPrefix()
                    success("Der Spieler wurde rotiert.")
                }
            }
        }
    }
}